package com.wearedevs.javaclicker.gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.wearedevs.javaclicker.Main;

@SuppressWarnings("serial")
public class ShopPanel extends JPanel {
	public JPanel upgradesPanel;
	public JLabel labelClicks;

	public ShopPanel() {
		setLayout(null);
		setBounds(Main.panelSize);
		
		JButton backBtn = new JButton("Go Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Main.updateCounter();
				
				Main.main.setContentPane(Main.mainPanel);
			}
		});
		backBtn.setBounds(12, 398, 270, 40);
		add(backBtn);
		
		upgradesPanel = new JPanel();
		upgradesPanel.setBounds(12, 75, 616, 311);
		add(upgradesPanel);
		upgradesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JLabel labelTitle = new JLabel("The Shop");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		labelTitle.setBounds(12, 13, 616, 67);
		add(labelTitle);
		
		labelClicks = new JLabel("You Have " + Main.clicks + " Clicks");
		labelClicks.setBounds(290, 410, 345, 16);
		
		labelClicks.setHorizontalAlignment(SwingConstants.RIGHT);
		labelClicks.setFont(new Font("Tahoma", Font.BOLD, 25));
		labelClicks.setBounds(282, 398, 346, 60);
		add(labelClicks);
	}
}