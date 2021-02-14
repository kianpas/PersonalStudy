package member.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import member.controller.MemberController;
import member.model.dao.MemberDao;
import member.model.vo.Member;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class UpdateMemberDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField MemberIdTextField;
	private JTextField passwordTextField;
	private JTextField nameTextField;
	private JTextField genderTextField;
	private JTextField ageTextField;
	private JTextField emailTextField;
	private JTextField phoneTextField;
	private JTextField addressTextField;
	private JTextField hobbyTextField;
	
	private MemberDao memberDao;
	private MemberSearchApp memberSearchApp;
	private MemberController memberController = new MemberController();
	private boolean updateMode = false;
	Member member = null;
	
	
	public UpdateMemberDialog(MemberSearchApp memberSearchApp, MemberDao memberDao, Member member, boolean updateMode) {
		this();
		this.memberSearchApp = memberSearchApp;
		this.memberDao = memberDao;
		this.member = member;
		this.updateMode = updateMode;
		if(this.updateMode) {
			setTitle("Update Member");
			
			//populateGui(member);
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateMemberDialog() {
		
		setTitle("Add Member");
		setBounds(100, 100, 450, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));
		{
			JLabel lblNewLabel = new JLabel("ID");
			contentPanel.add(lblNewLabel, "2, 2, left, default");
		}
		{
			MemberIdTextField = new JTextField();
			contentPanel.add(MemberIdTextField, "4, 2, fill, default");
			MemberIdTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Password");
			contentPanel.add(lblNewLabel_1, "2, 4, left, default");
		}
		{
			passwordTextField = new JTextField();
			contentPanel.add(passwordTextField, "4, 4, fill, default");
			passwordTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Name");
			contentPanel.add(lblNewLabel_2, "2, 6, left, default");
		}
		{
			nameTextField = new JTextField();
			contentPanel.add(nameTextField, "4, 6, fill, default");
			nameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Gender");
			contentPanel.add(lblNewLabel_3, "2, 8, left, default");
		}
		{
			genderTextField = new JTextField();
			contentPanel.add(genderTextField, "4, 8, fill, default");
			genderTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Age");
			contentPanel.add(lblNewLabel_4, "2, 10, left, default");
		}
		{
			ageTextField = new JTextField();
			contentPanel.add(ageTextField, "4, 10, fill, default");
			ageTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Email");
			contentPanel.add(lblNewLabel_5, "2, 12, left, default");
		}
		{
			emailTextField = new JTextField();
			contentPanel.add(emailTextField, "4, 12, fill, default");
			emailTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Phone");
			contentPanel.add(lblNewLabel_6, "2, 14, left, default");
		}
		{
			phoneTextField = new JTextField();
			contentPanel.add(phoneTextField, "4, 14, fill, default");
			phoneTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Address");
			contentPanel.add(lblNewLabel_7, "2, 16, left, default");
		}
		{
			addressTextField = new JTextField();
			contentPanel.add(addressTextField, "4, 16, fill, default");
			addressTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Hobby");
			contentPanel.add(lblNewLabel_8, "2, 18, left, default");
		}
		{
			hobbyTextField = new JTextField();
			contentPanel.add(hobbyTextField, "4, 18, fill, default");
			hobbyTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

					
						member = saveMember();
						System.out.println(member);
						
						memberController.insertMember(member);
					}

				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

		}

	}
	
	
	private Member saveMember() {
		
		Member member = new Member();
		String memberId = MemberIdTextField.getText();
		String password = passwordTextField.getText();
		String memberName = nameTextField.getText();
		String gender = genderTextField.getText();
		int age = Integer.parseInt(ageTextField.getText());
		String email = emailTextField.getText();
		String phone = phoneTextField.getText();
		String address = addressTextField.getText();
		String hobby = hobbyTextField.getText();
		
		member.setMemberId(memberId);
		member.setPassword(password);
		member.setMemberName(memberName);
		member.setGender(gender);
		member.setAge(age);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAddress(address);
		member.setHobby(hobby);
		
				
		try {
					
			//close dialog
			setVisible(false);
			dispose();
		
		memberSearchApp.refreshMemberview();
		
		//if(result > 0)
			JOptionPane.showMessageDialog(memberSearchApp, "Member added succesfully", "Member Added", JOptionPane.INFORMATION_MESSAGE);
		
		} catch(Exception e) {
			JOptionPane.showMessageDialog(memberSearchApp,	"Error saving Member: "+ e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			
			
		}
		return member;
		
	}

}
