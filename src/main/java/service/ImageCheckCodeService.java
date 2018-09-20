package service;

import java.awt.image.BufferedImage;

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

public class ImageCheckCodeService {

	private ImageCaptchaService instance = new DefaultManageableImageCaptchaService();

	/**
	 * Get default manageable image captcha service
	 * 
	 * @return ImageCaptchaService
	 */
	public BufferedImage getCheckCodeImage(String id) {

		return instance.getImageChallengeForID(id);

	}

}
