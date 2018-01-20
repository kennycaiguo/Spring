package vvr.web.action;

/**
 * �ͻ��ֵ���Ʋ�
 */
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import vvr.domain.Dict;
import vvr.service.DictService;
import vvr.web.utils.FastJsonUtil;

public class DictAction extends ActionSupport {

	private static final long serialVersionUID = -7043448609339477771L;
	
	private Dict dict;
	private DictService dictService;
	

	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}
	
	/**
	 * ��ѯ�ͻ��ֵ�Ŀͻ������ͻ���Դ
	 * @return
	 * @throws Exception
	 */
	public String findByCode() throws Exception{
		
		List<Dict> list = dictService.findByCode(dict.getDict_type_code());
		
		//ʹ��fastjson��listת����json�ַ���
		//List<Dict> list = dictService.findByCode("006");
		String jsonString = FastJsonUtil.toJSONString(list);
		//System.out.println(jsonString);
		
		//��json�ַ���д�뵽�����
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//����������
		FastJsonUtil.write_json(response, jsonString);
		
		return NONE;
	}
	

}
