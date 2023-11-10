import xml.etree.ElementTree as ET
import json

def parse_osm(xml_file):
    tree = ET.parse(xml_file)
    root = tree.getroot()

    data = {"Places": [], "ways": []}

    duplicates_list = []
    for node in root.findall(".//node"):
        node_data = {"id": node.attrib["id"], "lat": node.attrib["lat"], "lon": node.attrib["lon"]}
        for tag in node.findall("tag"):
            if tag.attrib["k"] == "name" or tag.attrib["k"] == "name:en" or tag.attrib["k"] == "contact:phone" or tag.attrib["k"] == "opening_hours" or tag.attrib["k"] == "website" or tag.attrib["k"] == "description" or tag.attrib["k"] == "addr:city":
                node_data[tag.attrib["k"]] = tag.attrib["v"]

        lat, lon = node.attrib["lat"][:5], node.attrib["lon"][:5]
        if (lat, lon) not in duplicates_list:
            duplicates_list.append((lat, lon))
            data["Places"].append(node_data)
        else:
            for elem in data["Places"]:
                if elem["lat"] == lat and elem["lon"] == lon:
                    elem.update(node_data)
                    break



    for way in root.findall(".//way"):
        way_tags = {}
        for tag in way.findall("tag"):
            if tag.attrib["k"] == "name" or tag.attrib["k"] == "name:en" or tag.attrib["k"] == "contact:phone" or tag.attrib["k"] == "opening_hours" or tag.attrib["k"] == "website" or tag.attrib["k"] == "description" or tag.attrib["k"] == "addr:city":
                way_tags[tag.attrib["k"]] = tag.attrib["v"]

        for nd in way.findall("nd"):
            ref_id = nd.attrib["ref"]
            node = next((n for n in data["Places"] if n["id"] == ref_id), None)
            if node:
                node.update(way_tags)

    data["Places"] = [node for node in data["Places"] if "name" in node]

    for node in data["Places"]:
        del node["id"]

    del data["ways"]

    return data

def main():
    osm_file = "cultural and historical heritage map.osm"
    json_file = "output.json"

    osm_data = parse_osm(osm_file)

    with open(json_file, "w", encoding="utf-8") as f:
        json.dump(osm_data, f, ensure_ascii=False, indent=2)

if __name__ == "__main__":
    main()