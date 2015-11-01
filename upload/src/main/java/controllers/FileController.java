package controllers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.File;
import utills.Algorithm;
import utills.Unzip;
import validator.FileValidator;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model) {
		File fileModel = new File();
		model.addAttribute("file", fileModel);
		return "file";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fileUploaded(Model model, @Validated File file, BindingResult result) throws InterruptedException {

		ConcurrentMap<String, Integer> wordCounterAG = new ConcurrentHashMap<String, Integer>();;
		ConcurrentMap<String, Integer> wordCounterHN = new ConcurrentHashMap<String, Integer>();;
		ConcurrentMap<String, Integer> wordCounterOU = new ConcurrentHashMap<String, Integer>();;
		ConcurrentMap<String, Integer> wordCounterVZ = new ConcurrentHashMap<String, Integer>();;
		String text = null;
		
		String returnVal = "successFile";
		if (result.hasErrors()) {
			returnVal = "file";
		} else {	
			text = Unzip.unZipIt(file.getFile());
			
			if(text == null || text.isEmpty()){
				returnVal = "file";
				return returnVal;
			}
			
			String[] parts = divideInHalf(text);
			Algorithm ag1 = new Algorithm(wordCounterAG, wordCounterHN, wordCounterOU, wordCounterVZ, parts[0]);
			Algorithm ag2 = new Algorithm(wordCounterAG, wordCounterHN, wordCounterOU, wordCounterVZ, parts[1]);
			ag1.join();
			ag2.join();
			model.addAttribute("answerAG", wordCounterAG);
			model.addAttribute("answerHN", wordCounterHN);
			model.addAttribute("answerOU", wordCounterOU);
			model.addAttribute("answerVZ", wordCounterVZ);
		}
		return returnVal;
	}
	
	private String[] divideInHalf(String text){
		int mid = text.length() / 2;
		while(true){
			if(text.charAt(mid) != ' '){
				mid++;
			}else{
				break;
			}
		}
		String[] parts = {
			text.substring(0, mid),
			text.substring(mid),
		};
		return parts;
	}
}