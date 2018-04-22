package com.cbwleft.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cbwleft.sms.model.dto.MessageDTO;
import com.cbwleft.sms.model.dto.SendMessageResult;
import com.cbwleft.sms.model.dto.ValidateCodeDTO;
import com.cbwleft.sms.model.vo.BaseResult;
import com.cbwleft.sms.model.vo.BaseResultEnum;
import com.cbwleft.sms.service.IMessageService;

@RestController
public class MessageController extends BaseController{
	
	@Autowired
	private IMessageService messageService;
	
	/**
	 * 发送短信
	 * @param message
	 * @return
	 */
	@PostMapping("/send")
	public BaseResult send(@RequestBody MessageDTO message) {
		SendMessageResult result = messageService.send(message);
		if(result.isSuccess()) {
			return new BaseResult(BaseResultEnum.SUCCESS, "发送成功");
		}else {
			return new BaseResult(BaseResultEnum.FAIL, result.getFailCode());
		}
	}
	
	/**
	 * 验证验证码
	 * @param validateCode
	 * @return
	 */
	@PostMapping("/check")
	public BaseResult check(@RequestBody ValidateCodeDTO validateCode) {
		boolean result = messageService.check(validateCode);
		if(result) {
			return new BaseResult(BaseResultEnum.SUCCESS, "验证通过");
		}else {
			return new BaseResult(BaseResultEnum.FAIL, "验证失败");
		}
	}

}
