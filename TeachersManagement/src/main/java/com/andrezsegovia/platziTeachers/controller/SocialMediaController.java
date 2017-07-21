package com.andrezsegovia.platziTeachers.controller;

import java.util.ArrayList;
import java.util.List;

import org.omg.PortableServer._ServantActivatorStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.andrezsegovia.platziTeachers.model.SocialMedia;
import com.andrezsegovia.platziTeachers.service.SocialMediaService;
import com.andrezsegovia.platziTeachers.utils.CustomErrorType;


@Controller
@RequestMapping("/v1")
public class SocialMediaController {
	
	@Autowired
	private SocialMediaService _socialMediaService;
	
	//GET
	@RequestMapping(value="/socialMedias", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<List<SocialMedia>> getSocialMedias(@RequestParam(value="name", required=false)String name){
		
		List<SocialMedia> socialMedias = new ArrayList<SocialMedia>();
		
		if(name == null) {
			socialMedias = _socialMediaService.findAllSocialMedia();
			if(socialMedias.isEmpty()) {
				return new ResponseEntity(new CustomErrorType("Social Medias not found"),HttpStatus.NO_CONTENT);
			}
			
		}else {
			SocialMedia socialMedia = _socialMediaService.findByName(name);
			if(socialMedia == null)
				return new ResponseEntity(new CustomErrorType("Social Media not found"),HttpStatus.NOT_FOUND);
			
			socialMedias.add(socialMedia);
		}
		
		return new ResponseEntity<List<SocialMedia>>(socialMedias,HttpStatus.OK);
	}
	
	//GET
	@RequestMapping(value="/socialMedias/{id}", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<SocialMedia> getSocialMediaById(@PathVariable("id") Long idSocialMedia) {
		if(idSocialMedia == null || idSocialMedia <= 0) {
			return new ResponseEntity(new CustomErrorType("idSocialMedia is required"),HttpStatus.CONFLICT);
		}
		
		SocialMedia socialMedia = _socialMediaService.findSocialMediaById(idSocialMedia);
		if(socialMedia == null) {
			return new ResponseEntity(new CustomErrorType("Social Media not found"),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity(socialMedia, HttpStatus.OK);	
	}
	
	//POST
	@RequestMapping(value="/socialMedias", method=RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<?> createSocialMedia(@RequestBody SocialMedia socialMedia, UriComponentsBuilder uriComponent){
		if(socialMedia.getName().equals(null) || socialMedia.getName().isEmpty()) {
			return new ResponseEntity(new CustomErrorType("Social Media name is required"),HttpStatus.CONFLICT);
		}
		
		if(_socialMediaService.findByName(socialMedia.getName()) != null) {
			return new ResponseEntity(new CustomErrorType("Social Media already exist"),HttpStatus.CONFLICT);
		}
		
		_socialMediaService.saveSocialMedia(socialMedia);
		SocialMedia socialMedia2 = _socialMediaService.findByName(socialMedia.getName());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriComponent.path("/v1/socialMedia/{id}")
				.buildAndExpand(socialMedia2.getIdSocialMedia())
				.toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.OK);
		
	}
	
	//UPDATE
	@RequestMapping(value="/socialMedias/{id}", method=RequestMethod.PATCH, headers="Accept=application/json")
	public ResponseEntity<?> updateSocialMedia(@PathVariable("id") Long idSocialMedia, @RequestBody SocialMedia socialMedia){
		if(idSocialMedia == null || idSocialMedia <= 0) {
			return new ResponseEntity(new CustomErrorType("Social Media Id is required"),HttpStatus.CONFLICT);
		}
		
		SocialMedia currentSocialMedia = _socialMediaService.findSocialMediaById(idSocialMedia);
		if(currentSocialMedia == null) {
			return new ResponseEntity(new CustomErrorType("Social Media not found"),HttpStatus.CONFLICT);
		}
		
		currentSocialMedia.setName(socialMedia.getName());
		currentSocialMedia.setIcon(socialMedia.getIcon());
		
		_socialMediaService.updateSocialMedia(currentSocialMedia);
		return new ResponseEntity<SocialMedia>(currentSocialMedia,HttpStatus.OK);
		
	}
	
	//DELETE
	@RequestMapping(value="/socialMedias/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteSocialMedia(@PathVariable("id") Long idSocialMedia){
		if(idSocialMedia == null || idSocialMedia <= 0) {
			return new ResponseEntity(new CustomErrorType("Social Media Id is required"),HttpStatus.CONFLICT);
		}
		
		SocialMedia socialMedia = _socialMediaService.findSocialMediaById(idSocialMedia);
		if(socialMedia == null)
			return new ResponseEntity(new CustomErrorType("Social Media not found"),HttpStatus.CONFLICT);
		
		_socialMediaService.deleteSocialMedia(idSocialMedia);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
