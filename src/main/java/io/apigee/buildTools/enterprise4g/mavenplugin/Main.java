package io.apigee.buildTools.enterprise4g.mavenplugin;

import java.io.IOException;

import io.apigee.buildTools.enterprise4g.rest.RestUtil;
import io.apigee.buildTools.enterprise4g.utils.ServerProfile;

public class Main {

	public static void main(String[] args) throws IOException {
		String sourceEvn = "dev";
		String destinationEnv = "demo";

		ServerProfile profile = new ServerProfile();
		profile.setHostUrl("https://api.enterprise.apigee.com");
		profile.setApplication("catalogue");

		profile.setApi_version("v1");
		profile.setTokenUrl("https://login.apigee.com/oauth/token");
		profile.setEnvironment(sourceEvn);
		String revisionSource = RestUtil.getDeployedRevision(profile);
		profile.setEnvironment(destinationEnv);
		String revisionDestination = RestUtil.getDeployedRevision(profile);
		System.out.println("Source Revision :" + revisionSource + " Destination Revision : " + revisionDestination);
		if (!revisionDestination.equalsIgnoreCase(revisionSource)) {
			RestUtil.Options.override=true;
			RestUtil.activateBundleRevision(profile, revisionSource);
		}
		
	}

}
