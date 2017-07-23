package com.andrezsegovia.platziTeachers.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.andrezsegovia.platziTeachers.model.SocialMedia;
import com.andrezsegovia.platziTeachers.model.Teacher;
import com.andrezsegovia.platziTeachers.model.TeacherSocialMedia;
import com.andrezsegovia.platziTeachers.service.SocialMediaService;
import com.andrezsegovia.platziTeachers.service.TeacherService;
import com.andrezsegovia.platziTeachers.utils.CustomErrorType;

@Controller
@RequestMapping("/v1")
public class TeacherController {
	
	@Autowired
	private TeacherService _teacherService;
	
	@Autowired
	private SocialMediaService _socialMediaService; 

	//POST
	@RequestMapping(value="/teachers", method=RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<?> createTeachers(@RequestBody Teacher teacher, UriComponentsBuilder uriComponentsBuilder){
		if(teacher == null)
			return new ResponseEntity<>(new CustomErrorType("Teacher is required"), HttpStatus.NOT_ACCEPTABLE);
		if(teacher.getName().equals(null) || teacher.getName().isEmpty())
			return new ResponseEntity<>(new CustomErrorType("Teacher name is required"), HttpStatus.NOT_ACCEPTABLE);
		if(_teacherService.findTeacherByName(teacher.getName()) != null)
			return new ResponseEntity<>(new CustomErrorType("Teacher already exist"), HttpStatus.NOT_ACCEPTABLE);
		
		_teacherService.saveTeacher(teacher);
		Teacher savedTeacher = _teacherService.findTeacherByName(teacher.getName());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriComponentsBuilder.path("/v1/teachers/{id}")
				.buildAndExpand(savedTeacher.getIdTeacher())
				.toUri());
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
	
	//GET
	@RequestMapping(value="/teachers", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<?> getTeachers(@RequestParam(value="name", required=false) String name,
			@RequestParam(value="idTeacher", required=false) Long idTeacher){		
		
		List<Teacher> teachers = new ArrayList<>();
		if(name != null && !name.isEmpty()) {
			Teacher teacher = _teacherService.findTeacherByName(name);
			if(teacher.equals(null))
				return new ResponseEntity<>(new CustomErrorType("Teacher with name "+name+" not found"), HttpStatus.NOT_FOUND);
			teachers.add(teacher);
		}else if(idTeacher != null && idTeacher >= 0 ) {
			Teacher teacher = _teacherService.findTeacherById(idTeacher);
			if(teacher.equals(null))
				return new ResponseEntity<>(new CustomErrorType("Teacher whit Id "+idTeacher+" not found"), HttpStatus.NOT_FOUND);
			teachers.add(teacher);
		}else {
			teachers = _teacherService.findAllTeachers();
			if(teachers.isEmpty())
				return new ResponseEntity<>(new CustomErrorType("Teachers not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK); 
			
	}
	
	//UPDATE
	@RequestMapping(value="/teachers/{id}", method=RequestMethod.PATCH, headers="Accept=application/json")
	public ResponseEntity<?> updateTeacher(@PathVariable("id") Long idTeacher, @RequestBody Teacher teacher){
		if(idTeacher == null || idTeacher < 0)
			return new ResponseEntity<>(new CustomErrorType("Teacher Id is required"), HttpStatus.CONFLICT);
		Teacher currentTeacher = _teacherService.findTeacherById(idTeacher);
		if(currentTeacher == null)
			return new ResponseEntity<>(new CustomErrorType("Teacher whit Id "+idTeacher+" not found"), HttpStatus.NOT_FOUND);
		currentTeacher.setName(teacher.getName());
		currentTeacher.setAvatar(teacher.getAvatar());
		_teacherService.updateTeacher(currentTeacher);
		return new ResponseEntity<>(currentTeacher, HttpStatus.OK);
	}
		
	//DELETE
	@RequestMapping(value="/teachers/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteTeacher(@PathVariable("id") Long idTeacher){
		if(idTeacher == null || idTeacher < 0)
			return new ResponseEntity<>(new CustomErrorType("Teacher Id is required"), HttpStatus.CONFLICT);
		Teacher currentTeacher = _teacherService.findTeacherById(idTeacher);
		if(currentTeacher == null)
			return new ResponseEntity<>(new CustomErrorType("Teacher whit Id "+idTeacher+" not found"), HttpStatus.NOT_FOUND);
		_teacherService.delateTeacher(currentTeacher.getIdTeacher());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private static final String TEACHER_UPLOADED_FOLDER = "images/teachers/";
	
	//UPLOAD TEACHER AVATAR
	@RequestMapping(value="/teachers/{id}", method=RequestMethod.POST, headers=("content-type=multipart/form-data"))
	public ResponseEntity<?> updateTeacherAvatar(@PathVariable("id") Long idTeacher,
			@RequestParam("file") MultipartFile multipartFile, UriComponentsBuilder uriComponentsBuilder){
		if(idTeacher == null || idTeacher < 0)
			return new ResponseEntity<>(new CustomErrorType("Teacher Id is required"), HttpStatus.CONFLICT);
		if(multipartFile.isEmpty())
			return new ResponseEntity<>(new CustomErrorType("Please select a file to upload"), HttpStatus.CONFLICT);
		
		Teacher teacher = _teacherService.findTeacherById(idTeacher);
		if(teacher == null)
			return new ResponseEntity<>(new CustomErrorType("Teacher whit Id "+idTeacher+" not found"), HttpStatus.NOT_FOUND);
		
		if(!teacher.getAvatar().isEmpty() || teacher.getAvatar() != null) {
			String fileName = teacher.getAvatar();
			Path path = Paths.get(fileName);
			File currentFile = path.toFile();
			if(currentFile.exists())
				currentFile.delete();
		}
		
		try {
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateName  = dateFormat.format(currentDate);
			
			String fileName = String.valueOf(idTeacher)+ "-avatarTeacher-" + dateName + "." +  multipartFile.getContentType().split("/")[1];
			teacher.setAvatar(TEACHER_UPLOADED_FOLDER+fileName);
			
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(TEACHER_UPLOADED_FOLDER+fileName);
			Files.write(path, bytes);
			
			_teacherService.updateTeacher(teacher);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(new CustomErrorType("Update Teacher's avatar Faild"), HttpStatus.NOT_FOUND);
		}
			
	}
	
	//GET IMAGE 
	@RequestMapping(value="/teachers/{id}/images", method=RequestMethod.GET)
	public ResponseEntity<?> getTeacherAvatar(@PathVariable("id") Long idTeacher){
		if(idTeacher == null || idTeacher < 0)
			return new ResponseEntity<>(new CustomErrorType("Teacher Id is required"), HttpStatus.CONFLICT);
		
		Teacher teacher = _teacherService.findTeacherById(idTeacher);
		if(teacher == null)
			return new ResponseEntity<>(new CustomErrorType("Teacher whit Id "+idTeacher+" not found"), HttpStatus.NOT_FOUND);
		
		try {
			String avatarPath = teacher.getAvatar();
			Path path = Paths.get(avatarPath);
			File avatar = path.toFile();
			if(!avatar.exists())
				return new ResponseEntity<>(new CustomErrorType("Teacher Avatar not found"), HttpStatus.NOT_FOUND);
			byte[] avatarBytes = Files.readAllBytes(path);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(avatarBytes);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(new CustomErrorType("Failed to get Avatar's Teacher"), HttpStatus.NOT_FOUND);
		}
	
	}
	
	//DELETE IMAGE
	@RequestMapping(value="/teachers/{id}/images", method=RequestMethod.DELETE, headers="Accept=application/json")
	public ResponseEntity<?> deleteTecherAvatar(@PathVariable("id") Long idTeacher){
		if(idTeacher == null || idTeacher < 0)
			return new ResponseEntity<>(new CustomErrorType("Teacher Id is required"), HttpStatus.CONFLICT);
		
		Teacher teacher = _teacherService.findTeacherById(idTeacher);
		if(teacher == null)
			return new ResponseEntity<>(new CustomErrorType("Teacher whit Id "+idTeacher+" not found"), HttpStatus.NOT_FOUND);
		
		String avatarPath = teacher.getAvatar();
		if(avatarPath.isEmpty() || avatarPath == null)
			return new ResponseEntity<>(new CustomErrorType("This Teacher doesn't has Avatar"), HttpStatus.NOT_FOUND);
		
		Path path = Paths.get(avatarPath);
		File avatarImage = path.toFile();
		if(!avatarImage.exists())
			return new ResponseEntity<>(new CustomErrorType("Teacher Avatar not found in the path"), HttpStatus.NOT_FOUND);
		if(avatarImage.delete()) {
			teacher.setAvatar("");
			_teacherService.updateTeacher(teacher);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new CustomErrorType("Failed to delete avartar's teacher"), HttpStatus.CONFLICT);
		}
	}
	
	// SET TEACHER_SOCIAL_MEDIA RELACION
	@RequestMapping(value="/teachers/socialMedias", method=RequestMethod.PATCH, headers="Accept=application/json")
	public ResponseEntity<?> assignTeacherSocialMedia(@RequestBody Teacher teacher, UriComponentsBuilder uriComponentsBuilder){
		if(teacher == null)
			return new ResponseEntity<>(new CustomErrorType("Teacher is required"), HttpStatus.CONFLICT);
		if(teacher.getIdTeacher() == null || teacher.getIdTeacher() < 0 )
			return new ResponseEntity<>(new CustomErrorType("We need Teacher's Id"), HttpStatus.CONFLICT);
		
		Teacher teacherSaved = _teacherService.findTeacherById(teacher.getIdTeacher());
		if(teacherSaved == null)
			return new ResponseEntity<>(new CustomErrorType("Teacher with Id "+teacher.getIdTeacher()+" not found"), HttpStatus.NOT_FOUND);
		
		if(teacher.getTeacherSocialMedias() == null ||teacher.getTeacherSocialMedias().isEmpty())
			return new ResponseEntity<>(new CustomErrorType("We need Teacher's Social Media"), HttpStatus.CONFLICT);
		else {
			//Itera por cada TeacherSocialMedia
			Iterator<TeacherSocialMedia> teacherSocialMediaIterator = teacher.getTeacherSocialMedias().iterator();
			while(teacherSocialMediaIterator.hasNext()) {
				TeacherSocialMedia teacherSocialMedia = teacherSocialMediaIterator.next();
				if((teacherSocialMedia.getSocialMedia() == null || 
						(teacherSocialMedia.getSocialMedia().getIdSocialMedia() == null ||
						teacherSocialMedia.getSocialMedia().getIdSocialMedia() < 0))||
						teacherSocialMedia.getNickName() == null)
					return new ResponseEntity<>(new CustomErrorType("We need Social Media's id and teacher's nickname"), HttpStatus.CONFLICT);
				
				SocialMedia socialMediaSaved = _socialMediaService.findSocialMediaById(teacherSocialMedia.getSocialMedia().getIdSocialMedia());
				if(socialMediaSaved==null)
					return new ResponseEntity<>(new CustomErrorType("Social Media with Id "+teacherSocialMedia.getSocialMedia().getIdSocialMedia()+" not found"), HttpStatus.NOT_FOUND);
				
				TeacherSocialMedia teacherSocialMediaSaved = _socialMediaService.findByIdSocialMediaAndIdTeacher(
						socialMediaSaved.getIdSocialMedia(),
						teacherSaved.getIdTeacher());
				if(teacherSocialMediaSaved != null) {
					for(TeacherSocialMedia tsm : teacherSaved.getTeacherSocialMedias()) {
						if(tsm.getSocialMedia().getIdSocialMedia().equals(socialMediaSaved.getIdSocialMedia()))
							
					}
					if(teacherSaved.getTeacherSocialMedias().remove(teacherSocialMediaSaved))
						teacherSocialMediaSaved.setNickName(teacherSocialMedia.getNickName());
				}
				else
					teacherSocialMediaSaved = new TeacherSocialMedia(teacherSaved,socialMediaSaved,teacherSocialMedia.getNickName());
				
				teacherSaved.getTeacherSocialMedias().add(teacherSocialMediaSaved);
			}
		}
		_teacherService.updateTeacher(teacherSaved);
		return new ResponseEntity<>(teacherSaved, HttpStatus.OK);
	}
}
