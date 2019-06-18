package com.github.wenzhencn.cmsseed.utils.tree.exception;

/**
 * 
 * @author wenzhen
 *
 */
public class LyridsTreeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LyridsTreeException() {
	}
	
	public LyridsTreeException(String message) {
		super(message);
	}
	
	public static LyridsTreeException newInstance(String message) {
		LyridsTreeException exception = new LyridsTreeException(message);
		return exception;
	}

}
