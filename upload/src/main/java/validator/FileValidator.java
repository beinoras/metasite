package validator;

import model.File;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {
	public boolean supports(Class<?> paramClass) {
		return File.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		File file = (File) obj;
		if (file.getFile().getSize() == 0) {
			errors.rejectValue("file", "valid.file");
		}
		
		String fName = file.getFile().getOriginalFilename();
		if(!fName.substring(fName.lastIndexOf(".") + 1).equals("zip")){
			errors.rejectValue("file", "valid.badFile");
		}
	}
}