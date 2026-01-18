package com.fiap.hackathon.global.base;

import com.fiap.hackathon.global.base.dto.BaseResponseDTO;

public abstract class BaseSuccessResponse<T extends BaseResponseDTO> extends BaseResponse {

	protected T item;

	public BaseSuccessResponse(int status) {
		super(status);
		this.success = true;
	}

	public BaseSuccessResponse(int status, T item) {
		super(status);
		this.success = true;
		this.item = item;
	}
}