package junghun.workbook.service;

import junghun.workbook.dto.MemberJoinDTO;

public interface MemberSevice {

	static class MidExistsException extends Exception {
	}

	void join(MemberJoinDTO memberJoinDTO) throws MidExistsException;
}
