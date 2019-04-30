package com.wexinc.interview.challenge1.util;

import java.util.Arrays;
import java.util.List;

public class Path {
	public static final String ThreadList = "/threads/";
	public static final String OneThread = "/threads/:threadId/";
	public static final String Login = "/login/";
	public static List<String> getEnpointsAuthorized =  Arrays.asList();
	public static List<String> postEnpointsAuthorized =  Arrays.asList(ThreadList);

	public static String getThreadlist() {
		return ThreadList;
	}

	public static String getOnethread() {
		return OneThread;
	}

	public static String getLogin() {
		return Login;
	}

}
