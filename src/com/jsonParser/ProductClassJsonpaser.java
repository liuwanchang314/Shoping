package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.productclasschild;
import com.bean.productclassgroup;
/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "class_id": "720",
            "class_name": "��ɫ����",
            "class_list": []
        },
        {
            "class_id": "721",
            "class_name": "��԰���",
            "class_list": []
        },
        {
            "class_id": "727",
            "class_name": "�ഺ����",
            "class_list": [
                {
                    "class_id": "745",
                    "class_name": "�ɰ���ͨ"
                },
                {
                    "class_id": "744",
                    "class_name": "���˷���"
                }
            ]
        }
    ],
    "status": 1
}
 * */
public class ProductClassJsonpaser {
	
	public static List<productclassgroup> getlist(String string){
		List<productclassgroup> list=new ArrayList<productclassgroup>();
		try {
			
			JSONObject  obj=new JSONObject(string);
			JSONArray array=obj.getJSONArray("data");
			int count=array.length();
			for(int i=0;i<count;i++){
				productclassgroup bean=new productclassgroup();
				JSONObject objs=array.getJSONObject(i);
				bean.setClass_id(objs.getString("class_id"));
				bean.setClass_name(objs.getString("class_name"));
				JSONArray arrays=objs.getJSONArray("class_list");
				//����һ�����ϣ������洢����Ŀ¼
				List<productclasschild> lists=new ArrayList<productclasschild>();
				for(int j=0;j<arrays.length();j++){
					
					productclasschild child=new productclasschild();
					JSONObject objss=arrays.getJSONObject(j);
					child.setClass_id(objss.getString("class_id"));
					child.setClass_name(objss.getString("class_name"));
					//�����������
					lists.add(child);
				}
				//��������������
				bean.setList(lists);
				list.add(bean);
				
				
				
				
			}
			
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

}
