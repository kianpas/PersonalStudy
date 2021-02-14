package member.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import member.model.vo.Member;

class MemberTableMdoel extends AbstractTableModel{
	
	public static final int OBJECT_COL = -1;
	private static final int MEMBER_ID = 0;
	private static final int PASSWORD_COL = 1;
	private static final int MEMBER_NAME_COL = 2;
	private static final int GENDER_COL = 3;
	private static final int AGE_COL = 4;
	private static final int EMAIL_COL = 5;
	private static final int PHONE_COL = 6;
	private static final int ADDRESS_COL = 7;
	private static final int HOBBY_COL = 8;
	private static final int ENOLLDATE_COL = 9;
	
	private String[] columnNames = {"Member Id", "Password", "Member Name", "Gender", "Age", "Email", "Phone", "Address", "Hobby", "EnollDate"};
	private List<Member> list;
	
	public MemberTableMdoel(List<Member> list) {
		this.list = list;
		
	}

	@Override
	public int getRowCount() {
		
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Member tempMember = list.get(rowIndex);
		
		switch (columnIndex) {
		case MEMBER_ID :
			return tempMember.getMemberId();
		case PASSWORD_COL :
			return tempMember.getPassword();
		case MEMBER_NAME_COL :
			return tempMember.getMemberName();
		case GENDER_COL :
			return tempMember.getGender();
		case AGE_COL :
			return tempMember.getAge();
		case EMAIL_COL :
			return tempMember.getEmail();
		case PHONE_COL :
			return tempMember.getPhone();
		case ADDRESS_COL :
			return tempMember.getAddress();
		case HOBBY_COL :
			return tempMember.getHobby();
		case ENOLLDATE_COL :
			return tempMember.getEnollDate();
		case OBJECT_COL:
			return tempMember;
		default :
			return tempMember.getMemberId();
		}
	
	
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
