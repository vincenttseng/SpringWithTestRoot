/**
 * 
 */
package com.vincent.coretest.util;

import com.vincent.coretest.exception.CommandSynTaxErrException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.insnergy.common.ccs.command.SystemCommand;
import com.insnergy.common.ccs.log.CommandLog;

/**
 * com.vincent.coretest.util CommandKafkaUtil.java
 *
 * @author vincenttseng 2019年8月29日
 */
public class CommandKafkaUtil {
	protected static final Logger logger = LoggerFactory.getLogger(CommandKafkaUtil.class);

	public static final String AP_IP_RESERVERED = "ap_ip_reserved";
	public static final String CUSTOMER_ID_RESERVED = "customer_id_reserved";
	public static final String CTRL_SOURCE_RESERVED = "ctrl_source_reserved";

	public static <T extends SystemCommand> String getCommandMsgWithStampInfo(String customerId, CommandLog.CtrlSource source, T command) {
		if (command == null) {
			return null;
		}

		String message = command.getCommand();
		JSONObject json = new JSONObject(message);
		json.put(AP_IP_RESERVERED, HttpUtils.getLocalIP());
		json.put(CUSTOMER_ID_RESERVED, customerId);
		json.put(CTRL_SOURCE_RESERVED, source != null ? source.getCode() : null);
		return json.toString();
	}

	public static <T extends SystemCommand> String getCommandMsgWithStampInfo(String customerId, CommandLog.CtrlSource source, String jsonString) {
		if (jsonString == null) {
			return null;
		}

		JSONObject json = new JSONObject(jsonString);
		json.put(AP_IP_RESERVERED, HttpUtils.getLocalIP());
		json.put(CUSTOMER_ID_RESERVED, customerId);
		json.put(CTRL_SOURCE_RESERVED, source != null ? source.getCode() : null);
		return json.toString();
	}

	public static String getScTarget(JSONObject json) throws CommandSynTaxErrException {
		try {
			return (String) json.get("sc_target");
		} catch (JSONException e) {
			throw new CommandSynTaxErrException("sc_target parse error");
		}
	}

	public static String getScProperty(JSONObject json) throws CommandSynTaxErrException {
		try {
			return (String) json.get("sc_property");
		} catch (JSONException e) {
			throw new CommandSynTaxErrException("sc_property parse error");
		}
	}

}
