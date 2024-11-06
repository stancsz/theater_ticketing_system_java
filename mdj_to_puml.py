import os
import json

def parse_mdj(mdj_file):
    with open(mdj_file, 'r') as file:
        data = json.load(file)
    return data

def build_reference_map(elements):
    """Create a mapping of element IDs to their names for reference lookup."""
    ref_map = {}
    for element in elements:
        if '_id' in element and 'name' in element:
            ref_map[element['_id']] = element['name']
        # Recursively build the map for nested ownedElements
        if 'ownedElements' in element:
            ref_map.update(build_reference_map(element['ownedElements']))
    return ref_map

def find_elements(element, element_type):
    elements = []
    if isinstance(element, dict):
        if element.get('_type') == element_type:
            elements.append(element)
        for sub_element in element.get('ownedElements', []):
            elements.extend(find_elements(sub_element, element_type))
    elif isinstance(element, list):
        for item in element:
            elements.extend(find_elements(item, element_type))
    return elements

def resolve_reference(ref, ref_map):
    """Resolve a reference ID to its corresponding name using the reference map."""
    if isinstance(ref, dict) and '$ref' in ref:
        ref = ref['$ref']
    return ref_map.get(ref, "UnknownType") if isinstance(ref, str) else "UnknownType"

def convert_to_puml(data):
    puml_lines = ['@startuml']

    # Build a reference map for all elements to resolve references
    ref_map = build_reference_map(data.get('ownedElements', []))

    # Convert classes
    classes = find_elements(data.get('ownedElements', []), 'UMLClass')
    for cls in classes:
        # Add documentation if available
        if 'documentation' in cls and cls['documentation'].strip():
            puml_lines.append(f"' {cls['documentation'].replace('\n', '\n\' ')}")
        
        # Add AI tag if available
        ai_tag = next((tag for tag in cls.get('tags', []) if tag['name'] == 'AI'), None)
        if ai_tag and 'value' in ai_tag:
            puml_lines.append(f"' AI_CMD: {ai_tag['value']}")
        
        # Class definition
        puml_lines.append(f'class {cls["name"]} {{')
        
        # Add attributes
        for attr in cls.get('attributes', []):
            visibility = attr.get('visibility', '')
            attr_type = resolve_reference(attr.get("type", ""), ref_map) if isinstance(attr.get("type", ""), dict) else attr.get("type", "")
            puml_lines.append(f'  {visibility} {attr["name"]} : {attr_type}')
        
        # Add operations with resolved parameter types
        for op in cls.get('operations', []):
            visibility = op.get('visibility', '')
            parameters = ', '.join(
                f'{param.get("name", "")}: {resolve_reference(param.get("type", ""), ref_map)}'
                for param in op.get('parameters', [])
            )
            return_type = resolve_reference(op.get("returnType", ""), ref_map) if isinstance(op.get("returnType", ""), dict) else op.get("returnType", "")
            puml_lines.append(f'  {visibility} {op.get("name", "")}({parameters}) : {return_type}')
        
        puml_lines.append('}')

    # Convert associations with resolved references
    associations = find_elements(data.get('ownedElements', []), 'UMLAssociation')
    for assoc in associations:
        end1 = assoc.get('end1', {})
        end2 = assoc.get('end2', {})

        # Resolve references for class names
        class1_name = resolve_reference(end1.get('reference', ''), ref_map)
        class2_name = resolve_reference(end2.get('reference', ''), ref_map)
        multiplicity1 = f'"{end1.get("multiplicity", "")}"' if end1.get("multiplicity") else ''
        multiplicity2 = f'"{end2.get("multiplicity", "")}"' if end2.get("multiplicity") else ''

        # Format association line with optional name and cleaner spacing
        association_line = f"{class1_name} {multiplicity1} -- {multiplicity2} {class2_name}"
        if assoc.get("name"):
            association_line += f' : {assoc["name"]}'
        
        puml_lines.append(association_line)
    
    puml_lines.append('@enduml')
    return '\n'.join(puml_lines)

def mdj_to_puml(mdj_file, puml_file):
    data = parse_mdj(mdj_file)
    puml_content = convert_to_puml(data)
    with open(puml_file, 'w') as file:
        file.write(puml_content)

def convert_all_mdj_to_puml(directory):
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith('.mdj'):
                mdj_path = os.path.join(root, file)
                puml_path = os.path.join(root, file.replace('.mdj', '.puml'))
                print(f'Converting {mdj_path} to {puml_path}')
                mdj_to_puml(mdj_path, puml_path)

if __name__ == '__main__':
    # Replace 'your_directory_path' with the path to the folder containing .mdj files
    directory = './'
    convert_all_mdj_to_puml(directory)
