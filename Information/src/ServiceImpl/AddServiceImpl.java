package ServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import Service.AddService;
import Service.CreateIndexService;

public class AddServiceImpl implements AddService {
	private CreateIndexService createIndexService;
	

	public void setCreateIndexService(CreateIndexService createIndexService) {
		this.createIndexService = createIndexService;
	}
	private String basePath=this.getClass().getClassLoader().getResource("/").getPath();
	@Override
	public boolean add(String path, String title, String content) {
		// TODO Auto-generated method stub
		try {
			String path0=basePath+path+"/";
			File file = new File(path0);
			if (!file.exists()) {
				file.mkdirs();
			}
			String path1 = path0 + title + ".txt";
			file = new File(path1);
			if (!file.exists()) {

				file.createNewFile();
			}
			FileOutputStream out= new FileOutputStream(file);
			 BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
		     buffer.write(content);
		     buffer.close();
		     createIndexService.createIndex(path);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
