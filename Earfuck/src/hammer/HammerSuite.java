package hammer;

import java.util.ArrayList;

public class HammerSuite {
	private String mName;
	private ArrayList<HammerTest> mTests;

	public HammerSuite(String name) {
		mName = name;
		mTests = new ArrayList<HammerTest>();
	}

	public void addTest(HammerTest test) {
		mTests.add(test);
	}
	
	public void run() {
		ArrayList<HammerTest> failedTests = new ArrayList<HammerTest>();
		
		// Silence test output.
		// In future this should only silence output to stdout.
		// The log file should still be written.
		HammerLog.setLogLevel(HammerLog.LogLevel.NONE);
		
		System.out.println("  === Running HAMMER suite: " + mName + " ===");
		
		for (HammerTest test : mTests) {
			if (test.run()) {
				System.out.print('.');
			}
			else {
				System.out.print('F');
				failedTests.add(test);
			}
		}
		
		int numTests = mTests.size();
		int failures = failedTests.size();
		
		System.out.println("\nTest suite complete: out of " + 
					 	   String.valueOf(numTests) + " tests, " + 
					 	   String.valueOf(failures) + " failed.\n");
		
		if (failures > 0) {
			System.out.print("Failed tests: ");
			for (HammerTest test : failedTests) {
				System.out.println("    " + test.getName());
			}
		}
	}
}
