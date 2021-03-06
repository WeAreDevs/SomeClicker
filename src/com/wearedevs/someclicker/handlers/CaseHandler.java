package com.wearedevs.someclicker.handlers;

import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;

import com.wearedevs.someclicker.Main;
import com.wearedevs.someclicker.cases.Case;
import com.wearedevs.someclicker.cases.CaseOutcome;
import com.wearedevs.someclicker.gui.CaseOpenPanel;
import com.wearedevs.someclicker.util.NotificationUtil;
import com.wearedevs.someclicker.util.PlaySound;
import com.wearedevs.someclicker.util.WeightedCollection;

public class CaseHandler {
	public static ArrayList<Case> caseList = new ArrayList<Case>();
	public static int caseSpd;
	public static int caseDelay;
	public static int caseDelay2;
	public static double caseGoal = 100;
	public static WeightedCollection<Case> cases = new WeightedCollection<Case>();
		
	/**
	 * extra c because {@code case} keyword
	 */
	public static Case ccase;
	public static WeightedCollection<CaseOutcome> caseOutcomes;
	
	public static void openCase(Case c) {
		ccase = c;
		caseOutcomes = c.getAllOutcomes();
		caseSpd = 0;
		caseDelay = 100;
		caseDelay2 = 0;
		
		Main.caseOpenPanel = new CaseOpenPanel(c);
		Main.main.setContentPane(Main.caseOpenPanel);
	}
	
	/**
	 * Used By {@code AutoHandler}
	 */
	public static void tickCase(Case caseOpening) {
		if(caseOutcomes==null) return;
		if(caseDelay>0) {
			caseDelay--;
			PlaySound.playSound("/sound/cases/tick.wav");
		}
		caseDelay2--;
		if(caseDelay2<=0) {
			//OPEN A CASE
			CaseOutcome open = caseOutcomes.next();
			if(caseDelay<=0) {
				caseSpd++;
				PlaySound.playSound("/sound/cases/tick.wav");
				
				if(caseSpd >= 30) {
					//Stop
					open.onOutcome();
					CaseHandler.caseList.remove(ccase);
					ccase = null;
					Main.caseOpenPanel.btnGoBack.setVisible(true);
					PlaySound.playSound("/sound/cases/outcome.wav");
				}
			}
			
			caseDelay2 = caseSpd;
			Main.caseOpenPanel.label_opening.setText(open.getName());
		}	
	}
	public static void unlock(Case c) {
		caseList.add(c);
		Main.casePanel.updateUI();
	}

	public static void checkCases() {
		if(Main.clicks >= caseGoal) {
			caseGoal *= 2;
			
			CaseHandler.unlock(cases.next());
			
			PlaySound.playSound("/sound/cases/get.wav");
			
			//TODO: Fix
			NotificationUtil.displayCaseNotif("New Case!", "You Have a New Case! Click Cases to Open It!", MessageType.INFO);
		}
	}
	public static void registerCase(Case c) {
		registerCase(100, c);
	}
	/**
	 * Adds a case to the random case giver.
	 * @param weight Weight for the case's appearence (100 for default chance)
	 * @param c The Case
	 */
	public static void registerCase(int weight, Case c) {
		cases.add(weight, c);
	}
}