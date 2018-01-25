package vvr.web.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import vvr.domain.Customer;
import vvr.domain.Dict;
import vvr.domain.PageBean;
import vvr.service.CustomerService;
import vvr.web.utils.UploadUtils;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>,RequestAware{

	/**
	 * �ͻ��߼���
	 */
	private static final long serialVersionUID = 1796491760814489483L;
	
	//ʹ��ģ��������װ���ݣ���Ҫnew������
	private Customer customer = new Customer();
	private CustomerService customerService;
	
	//��ǰҳ
	private Integer pageCode = 1;
	
	//һҳ��ʾ����������
	private Integer pageSize = 2;
	
	private Map<String,Object> request;
	
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setPageCode(Integer pageCode) {
		
		if(pageCode == null) {
			pageCode = 1;
		}
		
		this.pageCode = pageCode;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Customer getModel() {
		return customer;
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;	
	}
	
	/**
	 * �ļ��ϴ�����Ҫ��CustomerAction�ж������ԣ��������й����
	 * private File upload;	 ��ʾҪ�ϴ����ļ�
	 * private String uploadFileName;  ��ʾ�ϴ��ļ�������
	 * private String uploadContentType;  ��ʾ�ϴ����ļ�����
	 * 
	 * �ṩset������struts�������ͻὫ�ļ��������ע��
	 */
	
	/**
	 * ����ͻ��ķ���
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		
		//�ļ��ϴ�������û�ѡ�����ļ�
		if(uploadFileName != null) {
			
			//���ļ����ṩһ��Ψһ���ļ���
			String newFileName = UploadUtils.makeFileName(uploadFileName);
			
			//ָ���ϴ���·������Ҫ������Ŀ�е�·�����������������²����ļ���û�ˣ���ñ��浽tomcat��Ŀ¼��
			String path = "D:\\Tomact\\webapps\\upload\\";
			
			//����һ��file����
			File file = new File(path + newFileName);
			
			//�򵥵��ϴ��ļ���ʽ����ԭ�ļ�������ָ�����ļ�Ŀ¼�У�Apache�ṩ
			FileUtils.copyFile(upload, file);
			
			customer.setFilePath(path + newFileName);
		}
		
		customerService.save(customer);
		
		return "add";
	}
	
	
	/**
	 * �ṩ�ͻ���ҳ����
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception{
		
		//��ѯ������Ĭ��û��������ָ����Ҫ���ĸ�����
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		/**
		 * ��������ѯ��ƴ������
		 */
		String cust_name = customer.getCust_name();
		//�������Ŀͻ����Ʋ�Ϊ�ղ��ҿͻ����Ʋ�Ϊ���ַ���
		if(cust_name != null && !cust_name.trim().isEmpty()) {
			criteria.add(Restrictions.like("cust_name", "%"+ cust_name +"%"));
		}
		
		Dict level = customer.getLevel();
		//����ͻ�����Ϊ�գ����Ҳ�Ϊ�յ��ַ���
		if(level != null && !level.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		
		Dict source = customer.getSource();
		//����ͻ���Դ��Ϊ�գ����Ҳ�Ϊ�յ��ַ���
		if(source != null && !source.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		
		PageBean<Customer> page = customerService.findByPage(pageCode,pageSize,criteria);
		
		request.put("page", page);
		
		//��ɰ�������ѯ�����ݻ��ԣ������ֶ������ݼ���ֵջ��Ҳ���Բ��ã���Ϊ�ڰ�������ѯʱ���Ѿ���ָ�����ݷ�װ
		//��ģ���������ˡ�ֵջ�л���CustomerAction������󣬸ö�������set��get���������ԲŻ���뵽ֵջ��
		//����customer��ģ���������أ�������jsp�������ͨ��model��ȡֵ����ʱ��model����customer
		
		
		return "page";
	}
	
	/**
	 * �ṩ�ͻ���ӵ�UI����
	 * @return
	 * @throws Exception
	 */
	public String initAddUI() throws Exception{
		return "initAddUI";
	}
	
	/**
	 * ɾ��ָ���ͻ�����ɾ���ÿͻ��ϴ����ļ�
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		
		//�Ȳ�ѯ���ÿͻ���Ϣ������ϴ��ļ���·��
		customer = customerService.findById(customer.getCust_id());
		
		//��ȡ�ϴ��ļ���·��
		String filePath = customer.getFilePath();
		
		//ɾ���ͻ�
		customerService.delte(customer);
		
		//ɾ���ļ�
		File file = new File(filePath);
		//�������ļ�����
		if(file.exists()) {
			file.delete();
		}
		
		return "delete";
	}

	
	/**
	 * ��ʼ���ͻ��޸Ľ���
	 * @return
	 * @throws Exception
	 */
	public String initUpdate() throws Exception{
		
		//�õ��ͻ����ݣ���ʵ���Բ��ý��ÿͻ����ݱ�����request���У���customer�����Ѿ�����ֵջ�У�ֱ����
		//jsp������ͨ��modelȡ�����У�Ҳ���Դ棬����Ͳ�����
		customer = customerService.findById(customer.getCust_id());
		
		return "initUpdate";
	}
	
	/**
	 * �޸Ŀͻ�
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception{
		
		//����޸Ŀͻ��ϴ����µ��ļ�
		if(uploadFileName != null) {
			
			//ɾ���ɵ��ļ�
			String oldFilePath = customer.getFilePath();
			//����ɵ��ļ�·������
			if(oldFilePath != null && !oldFilePath.trim().isEmpty()) {
				File f = new File(oldFilePath);
				f.delete();
			}
			
			//�ϴ��µ��ļ�
			String newFileName = UploadUtils.makeFileName(uploadFileName);
			String path = "D:\\Tomact\\webapps\\upload\\";
			File file = new File(path + newFileName);
			FileUtils.copyFile(upload, file);
			
			//�������ݿ��иÿͻ����ļ�·��
			customer.setFilePath(path + newFileName);
		}
		
		//�޸Ŀͻ�
		customerService.update(customer);
		
		return "update";
	}

}
