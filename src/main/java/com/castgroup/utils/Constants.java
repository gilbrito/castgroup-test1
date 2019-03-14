package com.castgroup.utils;

/**
 * Stores all the interfaces that the software is responsible to handle.
 */
public interface Constants {
	public static final String DIFF_LEFT = "/diff/{id}/left";
	public static final String DIFF_RIGHT = "/diff/{id}/right";
	public static final String DIFF_RESULT = "/diff/{id}/result";
	public enum DiffType{
		LEFT,
		RIGHT;
	};
}