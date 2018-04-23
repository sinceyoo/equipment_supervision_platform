package com.syo.platform.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.syo.platform.entity.Article;
import com.syo.platform.entity.ArticleAttachment;
import com.syo.platform.entity.User;
import com.syo.platform.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Value("${attachment.article.path}")
	private String attachmentRootPath;
	
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable("id")String id, Model model) {
		model.addAttribute("article", articleService.findArticleById(id));
		return "article_detail";
	}
	
	@RequestMapping({"","/","articles"})
	public String articles(@RequestParam(defaultValue="1")int pageNo, @RequestParam(defaultValue="10")int pageSize, Date start, Date end, String vague, Model model, HttpServletRequest request) {
		model.addAttribute("vague", vague);
		model.addAttribute("allArticles", articleService.findArticle(start, end, pageNo, pageSize, vague));
//		model.addAttribute("myArticles", articleService.findArticleByAccount(loginUser.getAccount(), start, end, pageNo, pageSize, vague));
		model.addAttribute("myArticles", articleService.findArticleByAccount("admin", start, end, pageNo, pageSize, vague));
		return "articles";
	}
	
	@RequestMapping("/writing/{id}")
	public String writeArticle(@PathVariable("id")String id, Model model) {
		Article article = articleService.findArticleById(id);
		model.addAttribute("article", article);
		return "article_writing";
	}
	
	@RequestMapping("/writing")
	public String writeArticle(Model model) {
		model.addAttribute("article", new Article());
		return "article_writing";
	}
	
	@RequestMapping("/save")
	public String save(Article article, MultipartFile attachment) {

		File dir = null;
		File targetFile = null;
		if(attachment.getSize()>0) {
			String trueFileName = attachment.getOriginalFilename();		
			String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
			String fileName = UUID.randomUUID()+suffix;
			
			ArticleAttachment att = new ArticleAttachment();
			att.setFileName(attachment.getOriginalFilename());
			att.setFileType(attachment.getContentType());
			att.setFileSize(attachment.getSize());
			att.setDisk("G:");
			String path = attachmentRootPath+new SimpleDateFormat("yyyyMMdd").format(att.getCreateTime())+"/";
			att.setFilePath(path+fileName);
			att.setArticle(article);
			
			List<ArticleAttachment> atts = new ArrayList<>();
			atts.add(att);
			article.setAttachments(atts);
			
			dir = new File(att.getDisk()+path);
			targetFile = new File(att.getDisk()+att.getFilePath());
		}
		
		articleService.saveArticle(article);
		
		if(dir!=null && targetFile!=null) {
			if(!dir.exists()) {
				dir.mkdirs();
			}
			//保存
			try {
				attachment.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	
	
		return "redirect:writing";
	}
	
	@RequestMapping("/editormdPic")
	@ResponseBody
	public Map<String, Object> editormdPic(@RequestParam(value="editormd-image-file",required=true)MultipartFile file, HttpServletRequest request) {
		
		String trueFileName = file.getOriginalFilename();
		
		String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
		String fileName = UUID.randomUUID()+suffix;
		
//		User loginUser = null;
//		String path = request.getSession().getServletContext().getRealPath("/material/"+loginUser.getAccount()+"/article/image/");
		
		String path = request.getSession().getServletContext().getRealPath("/material/admin/article/image/");
		
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		File targetFile = new File(path, fileName);
		
		//保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		Map<String, Object> res = new HashMap<String, Object>();
//		res.put("url", "material/"+loginUser.getAccount()+"/article/image/"+fileName);
		res.put("url", "material/admin/article/image/"+fileName);
		res.put("success", 1);
		res.put("message", "upload success!");
		
		return res;
	}
	
}
