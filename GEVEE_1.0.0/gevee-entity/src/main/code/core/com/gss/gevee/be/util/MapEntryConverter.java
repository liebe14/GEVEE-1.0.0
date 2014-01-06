package com.gss.gevee.be.util;

import java.util.Map;
import java.util.Map.Entry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@SuppressWarnings("unchecked")
public class MapEntryConverter implements Converter {

	@Override
    public boolean canConvert(Class clazz) {
		System.out.println("Vérification de la possibilité de Convertion du type : " + clazz.getName());
       return Map.class.isAssignableFrom(clazz);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
    	Map map = null;
    	if(value != null)
    		map = (Map) value;
    	else
    		return;
    	
        for (Object obj : map.entrySet()) {
        	if(obj == null){
        		continue;
        	}
        	Entry entry = (Entry) obj;
        	
        	Object key = entry.getKey();
        	if(key != null){
        		writer.startNode(key.toString());
        	}
        	else{
        		continue;
        	}            
            
            Object val = entry.getValue();
            if(val != null){
            	writer.setValue(val.toString());
            }
            else{
            	writer.setValue("");
            }
            
            writer.endNode();
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return null;
    }
}

