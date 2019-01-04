package xyz.dymasun.resume.rule;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RuleList extends BaseRule {

    private List list;

    private RuleList(){
        this.list = new ArrayList();
    }
    public static RuleList create(){
        return new RuleList();
    }
    public RuleList add(Object obj){
        list.add(obj);
        return this;
    }
    public RuleList add(List param){
        for(Object obj: param){
            list.add(obj);
        }
        return this;
    }
    public RuleList add(RuleMap map){
        list.add(map.getMap());
        return this;
    }
    @Override
    public String toJSON() {
        return JSONObject.toJSONString(list);
    }

    public RuleResult result(){
        return RuleResult.success(this.list);
    }
    public List toArray(){
        return list;
    }
}