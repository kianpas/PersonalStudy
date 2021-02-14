package member.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import member.controller.MemberController;
import member.model.dao.MemberDao;
import member.model.vo.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MemberSearchApp extends JFrame {

	private JPanel contentPane;
	private JTextField memberNameTextFiel;
	private JTable table;
	private MemberController memberController = new MemberController();
	private MemberDao memberDao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberSearchApp frame = new MemberSearchApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemberSearchApp() {
		setTitle("MemberSearchApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Enter Member Name");
		panel.add(lblNewLabel);
		
		memberNameTextFiel = new JTextField();
		panel.add(memberNameTextFiel);
		memberNameTextFiel.setColumns(20);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//멤버이름 텍스트필드
				
				
				String memberName = memberNameTextFiel.getText();
				
				//dao 불러오기
				List<Member> memberList = null;
				
				if(memberName != null) {
					memberList = memberController.selectNameList(memberName);
								
				} else {
					memberList = memberController.selectAll();
				}
				
//				for(Member m : memberList) {
//					System.out.println(m);
//				}
				  
				MemberTableMdoel model = new MemberTableMdoel(memberList);
				table.setModel(model);
				
			}
		});
		panel.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddMemberDialog dialog = new AddMemberDialog(MemberSearchApp.this, memberDao);
				
				dialog.setVisible(true);
			}
		});
		
		panel_1.add(btnAddMember);
		
		JButton btnUpdateMember = new JButton("Update Mem.");
		btnUpdateMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				if(row < 0) {
					JOptionPane.showMessageDialog(MemberSearchApp.this, "you must Select a Member", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Member tempMember = (Member) table.getValueAt(row, MemberTableMdoel.OBJECT_COL);
				
				AddMemberDialog dialog = new AddMemberDialog(MemberSearchApp.this, memberDao, tempMember, true);
				
				dialog.setVisible(true);
			}
		});
		panel_1.add(btnUpdateMember);
		
		JButton btnDelMember = new JButton("Delete Member");
		btnDelMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int row = table.getSelectedRow();
					
					if(row < 0 ) {
						JOptionPane.showMessageDialog(MemberSearchApp.this, "You Must select a Member", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int response = JOptionPane.showConfirmDialog(MemberSearchApp.this, "Delete this Member?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if(response != JOptionPane.YES_OPTION) {
						return;
					}
					
					Member member = (Member) table.getValueAt(row, MemberTableMdoel.OBJECT_COL);
					
					memberController.deleteMember(member.getMemberId());
					
					refreshMemberview();
					
					//성공메시지
					
					JOptionPane.showMessageDialog(MemberSearchApp.this, "Member delete Succesfully.", "Member Deleted", JOptionPane.INFORMATION_MESSAGE);
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(MemberSearchApp.this, "Error deleteing Member : " + e1.getMessage(), "Error",
							 JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		panel_1.add(btnDelMember);
	}

	public void refreshMemberview() {
		
		try {
			List<Member> list = memberController.selectAll();
			
			MemberTableMdoel model = new MemberTableMdoel(list);
			table.setModel(model);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
