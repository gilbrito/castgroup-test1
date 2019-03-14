package com.castgroup.service;

import static com.castgroup.utils.Constants.DiffType;;

public interface DiffService {
	public void save(String payload, Integer id, DiffType diffType);
	public String getDiff(Integer id);
}
