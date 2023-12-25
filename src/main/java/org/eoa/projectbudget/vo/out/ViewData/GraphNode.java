package org.eoa.projectbudget.vo.out.ViewData;


import org.eoa.projectbudget.vo.out.DepartOut;
import org.eoa.projectbudget.vo.out.SectionOut;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/25 11:05
 * @PackageName: org.eoa.projectbudget.vo.out.ViewData
 * @ClassName: GraphNode
 * @Description: 组织结构树节点结构
 * @Version: 1.0
 **/
public class GraphNode {
    String id;
    Value value;
    List<GraphNode> children;

    public static class Value {
        String title;
        String name;
        String id;
        String type;
        String icon;
        String isDeprecated;

        public Value() {
        }

        public String getTitle() {
            return title;
        }

        public Value setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getName() {
            return name;
        }

        public Value setName(String name) {
            this.name = name;
            return this;
        }

        public String getId() {
            return id;
        }

        public Value setId(String id) {
            this.id = id;
            return this;
        }

        public String getType() {
            return type;
        }

        public Value setType(String type) {
            this.type = type;
            return this;
        }

        public String getIcon() {
            return icon;
        }

        public Value setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public String getIsDeprecated() {
            return isDeprecated;
        }

        public Value setIsDeprecated(String isDeprecated) {
            this.isDeprecated = isDeprecated;
            return this;
        }

        public Value(String title, String name, String id, String type, String icon) {
            this.title = title;
            this.name = name;
            this.id = id;
            this.type = type;
            this.icon = icon;
            this.isDeprecated = "0";
        }

        public Value(String title, String name, String id, String type, String icon, String isDeprecated) {
            this.title = title;
            this.name = name;
            this.id = id;
            this.type = type;
            this.icon = icon;
            this.isDeprecated = isDeprecated;
        }
    }

    public GraphNode(String id, String title, String name, String type, String icon) {
        this.id = type + id;
        this.value = new Value(title, name, id, type, icon);
    }

    public GraphNode(String id, String title, String name, String type, String icon, String isDeprecated) {
        this.id = type + id;
        this.value = new Value(title, name, id, type, icon, isDeprecated);
    }

    public GraphNode consist(List<SectionOut> sectionOuts, List<DepartOut> departOuts) {
        Long id = Long.parseLong(this.value.id);
        children = new ArrayList<>();
        List<SectionOut> belongSection = sectionOuts.stream().filter(sectionOut -> id.equals(sectionOut.getBelongSection())).toList();
        sectionOuts.removeAll(belongSection);
        List<GraphNode> sectionNodes = belongSection.stream().map(sectionOut -> new GraphNode(sectionOut.getDataId().toString(), sectionOut.getSectionName(), sectionOut.getSectionCode(), "section", null,sectionOut.getIsDeprecated().toString())).toList();
        List<DepartOut> belongDeparts = departOuts.stream().filter(departOut -> id.equals(departOut.getBelongSection()) && departOut.getBelongDepart() == null).toList();
        departOuts.removeAll(belongDeparts);
        List<GraphNode> departNodes = belongDeparts.stream().map(departOut -> new GraphNode(departOut.getDataId().toString(), departOut.getDepartName(), departOut.getDepartCode(), "depart", null,departOut.getIsDeprecated().toString())).toList();

        sectionNodes.forEach(graphNode -> graphNode.consist(sectionOuts,departOuts));
        departNodes.forEach(graphNode -> graphNode.consistDepart(departOuts));

        children.addAll(sectionNodes);
        children.addAll(departNodes);

        return this;
    }

    public void consistDepart(List<DepartOut> departOuts) {
        Long id = Long.parseLong(this.value.id);

        List<DepartOut> belongDeparts = departOuts.stream().filter(departOut -> id.equals(departOut.getBelongDepart())).toList();
        departOuts.removeAll(belongDeparts);
        List<GraphNode> departNodes = belongDeparts.stream().map(departOut -> new GraphNode(departOut.getDataId().toString(), departOut.getDepartName(), departOut.getDepartCode(), "depart", null,departOut.getIsDeprecated().toString())).toList();
        departNodes.forEach(graphNode -> consistDepart(departOuts));
        children = departNodes;

    }

    public GraphNode() {
    }

    public String getId() {
        return id;
    }

    public GraphNode setId(String id) {
        this.id = id;
        return this;
    }

    public Value getValue() {
        return value;
    }

    public GraphNode setValue(Value value) {
        this.value = value;
        return this;
    }

    public List<GraphNode> getChildren() {
        return children;
    }

    public GraphNode setChildren(List<GraphNode> children) {
        this.children = children;
        return this;
    }
}
