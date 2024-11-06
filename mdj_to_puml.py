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
        if 'ownedElements' in element:
            ref_map.update(build_reference_map(element['ownedElements']))
    return ref_map

def find_all_elements(element):
    """Recursively find all elements."""
    elements = []
    if isinstance(element, dict):
        elements.append(element)
        for sub_element in element.get('ownedElements', []):
            elements.extend(find_all_elements(sub_element))
    elif isinstance(element, list):
        for item in element:
            elements.extend(find_all_elements(item))
    return elements

def resolve_reference(ref, ref_map):
    """Resolve a reference ID to its corresponding name using the reference map."""
    if isinstance(ref, dict) and '$ref' in ref:
        ref = ref['$ref']
    return ref_map.get(ref, "UnknownType") if isinstance(ref, str) else "UnknownType"

def convert_visibility(visibility):
    """Convert visibility keywords to PlantUML symbols."""
    visibility_map = {
        'public': '+',
        'private': '-',
        'protected': '#',
        'package': '~'
    }
    return visibility_map.get(visibility, '')  # Default to empty if visibility is unknown

def convert_to_puml(data):
    puml_lines = ['@startuml']
    
    # Build a reference map for all elements to resolve references
    ref_map = build_reference_map(data.get('ownedElements', []))
    
    # Find all elements to handle them generically
    all_elements = find_all_elements(data.get('ownedElements', []))
    for element in all_elements:
        element_type = element.get('_type', 'UnknownType')
        
        # Map StarUML types to PlantUML
        if element_type == 'UMLClass':
            element_keyword = 'class'
        elif element_type == 'UMLInterface':
            element_keyword = 'interface'
        elif element_type == 'UMLAbstractClass':
            element_keyword = 'abstract class'
        elif element_type == 'UMLPackage':
            element_keyword = 'package'
        else:
            continue  # Skip unsupported element types

        # Check for valid element name
        element_name = element.get('name')
        if not element_name:
            continue  # Skip unnamed elements

        puml_lines.append(f"{element_keyword} {element_name} {{")
        
        # Add attributes generically
        for attr in element.get('attributes', []):
            attr_visibility = convert_visibility(attr.get('visibility', ''))
            attr_type = resolve_reference(attr.get("type", ""), ref_map) if isinstance(attr.get("type", ""), dict) else attr.get("type", "")
            puml_lines.append(f"  {attr_visibility} {attr['name']} : {attr_type}")
        
        # Add operations generically
        for op in element.get('operations', []):
            op_visibility = convert_visibility(op.get('visibility', ''))
            parameters = ', '.join(
                f'{param.get("name", "")}: {resolve_reference(param.get("type", ""), ref_map)}'
                for param in op.get('parameters', [])
            )
            return_type = resolve_reference(op.get("returnType", ""), ref_map) if isinstance(op.get("returnType", ""), dict) else op.get("returnType", "")
            puml_lines.append(f"  {op_visibility} {op.get('name', '')}({parameters}) : {return_type}")
        
        puml_lines.append("}")

    # Convert associations with resolved references
    for element in all_elements:
        element_type = element.get('_type')
        if element_type not in ['UMLAssociation', 'UMLGeneralization', 'UMLDependency']:
            continue

        end1 = element.get('end1', {})
        end2 = element.get('end2', {})

        class1_name = resolve_reference(end1.get('reference', ''), ref_map)
        class2_name = resolve_reference(end2.get('reference', ''), ref_map)
        if class1_name == "UnknownType" or class2_name == "UnknownType":
            continue  # Skip invalid relationships

        # Handle multiplicities if available
        multiplicity1 = f'"{end1.get("multiplicity", "")}"' if end1.get("multiplicity") else ''
        multiplicity2 = f'"{end2.get("multiplicity", "")}"' if end2.get("multiplicity") else ''

        # Choose relationship symbol
        if element_type == 'UMLAssociation':
            relationship = "--"
        elif element_type == 'UMLGeneralization':
            relationship = "<|--"
        elif element_type == 'UMLDependency':
            relationship = "<.."

        association_line = f"{class1_name} {multiplicity1} {relationship} {multiplicity2} {class2_name}"
        if element.get("name"):
            association_line += f' : {element["name"]}'
        
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
