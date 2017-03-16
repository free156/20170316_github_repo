
package com.j1.mai.action;

import com.j1.common.JsonpBaseAction;
import com.j1.common.MsgStatus;
import com.j1.common.StringUtil;
import com.j1.mai.Entity.AsnEntity;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 */
@Controller
@Scope("request")
@RequestMapping("/main")
public class OrderAction2  extends JsonpBaseAction{

	static final Logger LOG = Logger.getLogger(OrderAction2.class);


	/**
	 * san查询
	 * @param request
	 * @param response
	 * @param asnId
	 * @param asnNo
	 * @param asnDateStr
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public String detail(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "asnId", required = false) Long asnId,
			@RequestParam(value = "asnNo", required = false) String asnNo,
			@RequestParam(value = "asnDateStr", required = false) String asnDateStr) {

		String asnId1 = request.getParameter("asnId");

		if (StringUtil.isEmpty(asnNo)) {
			setResultInfo(MsgStatus.PARAMS_ERROR.getCode(), "参数错误").write(request, response);
			return _result.toString();
		}

		if (asnId==null) {
			setResultInfo(MsgStatus.PARAMS_ERROR.getCode(), "参数错误").write(request, response);
			return _result.toString();
		}
		if (StringUtil.isEmpty(asnDateStr)) {
			setResultInfo(MsgStatus.PARAMS_ERROR.getCode(),"参数错误").write(request, response);
			return _result.toString();
		}
		try{
			AsnEntity asnEntity = makeDate();
			if (asnEntity!=null){
				this._result.setStatus(MsgStatus.NORMAL.getCode()).setMsg("查询成功");
//				_result.setObjData(asnEntity);
				_result.put("asnEntity",asnEntity);
			}else {
				this._result.setStatus(MsgStatus.NO_RESULT.getCode()).setMsg("未找到结果");
			}

//			_result.put("list",makeList());
//			_result.put("key","value");

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
//			write(request, response);
			return _result.toString();
		}
	}

	private AsnEntity makeDate(){
		AsnEntity asnEntity=new AsnEntity();
		asnEntity.setAsnId(111);
		asnEntity.setAsnNo("aaaa");
//		asnEntity.setAsnDateStr("bbbb");
		return  asnEntity;
	}

	private List makeList(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		return list;
	}

}
