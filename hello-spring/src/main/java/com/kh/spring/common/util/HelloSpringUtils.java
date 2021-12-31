package com.kh.spring.common.util;

public class HelloSpringUtils {

	
	/**
	 * 
	 * @param cPage
	 * @param numPerPage
	 * @param totalContent
	 * @param url
	 * 
	 * totalPage 전체페이지
	 * pagebarSize 페이지바 크기 5
	 * pageNo
	 * pageStart - pageEnd
	 * 
	 * @return
	 */
	public static String getPagebar(int cPage, int numPerPage, int totalContent, String url) {
		StringBuilder pagebar = new StringBuilder(); 
		url = url + "?cPage="; // pageNo 추가전 상태
		
		final int pagebarSize = 5;
		final int totalPage = (int) Math.ceil((double) totalContent / numPerPage);
		final int pageStart = (cPage - 1) / pagebarSize * pagebarSize + 1;
		int pageEnd = pageStart + pagebarSize - 1;
		pageEnd = totalPage < pageEnd ? totalPage : pageEnd;
		int pageNo = pageStart;

		// [이전]
		if(cPage == 1) {
			pagebar.append("<li class='page-item disabled'><a class='page-link' href=" + "?cPage=" + (cPage - 1) + ">Previous</a></li>");
		} else {
			pagebar.append("<li class='page-item'><a class='page-link' href=" + "?cPage=" + (cPage - 1) + ">Previous</a></li>");
		}
		// pageNo
		while(pageNo <= pageEnd) {
			if(pageNo == cPage) {
				pagebar.append("<li class='page-item active'><a class='page-link' href=" + "?cPage=" + pageNo + ">" + pageNo +"</a></li>");
			} else {
				pagebar.append("<li class='page-item'><a class='page-link' href=" + "?cPage=" + pageNo + ">" + pageNo +"</a></li>");
			}
			
			pageNo++;
		}
		
		
		
		// [다음]
		if(cPage == totalPage) {
			pagebar.append("<li class='page-item disabled'><a class='page-link' href=" + "?cPage=" + (cPage + 1) + ">Next</a></li>");
		} else {
			pagebar.append("<li class='page-item'><a class='page-link' href=" + "?cPage=" + (cPage + 1) + ">Next</a></li>");
		}
		
		return pagebar.toString();
	}

}
