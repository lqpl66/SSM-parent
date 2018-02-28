package com.yl.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Advertisement;
import com.utils.Constant;
import com.yl.mapper.AdMapper;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/comm")
public class CommonController {
	@Autowired
	private AdMapper adMapper;
	private Logger log = Logger.getLogger(CommonController.class);

	/**
	 * 
	 * @description 获取广告位
	 * @param type
	 *            1：一级广告位；2：首页热点推荐广告位
	 * @return
	 */
	@SuppressWarnings({ "finally" })
	@ResponseBody
	@RequestMapping(value = "/getAdList", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String getAdList(HttpServletRequest request, @RequestBody String jsonparam) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Advertisement> list = adMapper.getBannerList();
			if (list.size() > 0) {
//				for (Advertisement ad : list) {
//					if (ad.getImg() != null && !ad.getImg().isEmpty()) {
//						ad.setImg(url + ad.getImg());
//					}
//				}
			}
			result.put("data", list);
			result.put(Constant.code.CODE, Constant.code.CODE_1);
			result.put(Constant.message.MESSAGE, Constant.message.MESSAGE_1);
		} catch (Exception e) {
			log.error("获取广告位：", e);
			e.printStackTrace();
			result.put(Constant.code.CODE, Constant.code.CODE_0);
			result.put(Constant.message.MESSAGE, Constant.message.MESSAGE_0);
		} finally {
			return result.toString();
		}
	}


}
