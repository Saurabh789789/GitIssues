package com.dev.gitissues;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//A basic rest api

@Configuration
@EnableWebMvc
@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class GitController {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	String repoResult(Repo repo, Model model) {
		// splitting the input URL to get user name and repo name
		String[] split = repo.geturl().split("/");
		GitHubClient client = new GitHubClient();

		

		IssueService s = new IssueService(client);

		Map<String, String> filterData = new HashMap();

		try {

			Calendar now = Calendar.getInstance();
			StringBuilder formattedTime1 = new StringBuilder();
			StringBuilder formattedTime2 = new StringBuilder();
			// getting current date and time
			now = Calendar.getInstance();
			// getting last day and time i.e before 24 hrs
			now.add(Calendar.DATE, -1);
			formattedTime1 = convertDate(now);
			// getting date and time of 7 days before
			// subtracting 6 because we have already subtracted 1 above from
			// "now" object
			now.add(Calendar.DATE, -6);
			formattedTime2 = convertDate(now);
			// split[3] contains username and split[4] contains repo name
			filterData.put("state", "open");
			// created a filter to fetch open issues

			filterData.put("since", new String(formattedTime1));
			// calling github api to fetch issues

			List<Issue> totalIssue = s.getIssues(split[3], split[4], null);
			List<Issue> issues1 = s.getIssues(split[3], split[4], filterData);
			filterData.put("since", new String(formattedTime2));
			List<Issue> issues2 = s.getIssues(split[3], split[4], filterData);

			model.addAttribute("total_number_of_Issues", totalIssue.size());
			model.addAttribute("last_24_hour", issues1.size());
			model.addAttribute("last_24_to_7_days",
					issues2.size() - issues1.size());

			model.addAttribute("more_than_7",
					totalIssue.size() - issues2.size());

		}

		catch (Exception e) {
			System.out.print(e.toString());
		}

		return "result";

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String repoParameter(Repo repo) {

		return "index";

	}

	StringBuilder convertDate(Calendar now) {
		// This is used to format date according to github api date format to
		// get issues
		StringBuilder dateStr = new StringBuilder();
		dateStr = dateStr.append(now.get(Calendar.YEAR) + "-");
		if (Integer.toString(now.get(Calendar.MONTH) + 1).trim().length() == 2) {
			dateStr.append((now.get(Calendar.MONTH) + 1));
		}

		else {
			dateStr.append("0" + (now.get(Calendar.MONTH) + 1));
		}
		dateStr.append("-");
		if (Integer.toString(now.get(Calendar.DATE)).trim().length() == 2) {
			dateStr.append(now.get(Calendar.DATE));
		} else {
			dateStr.append("0" + now.get(Calendar.DATE));
		}
		dateStr.append("T");
		dateStr.append(now.get(Calendar.HOUR_OF_DAY) + ":"
				+ now.get(Calendar.MINUTE) + ":");
		if (Integer.toString(now.get(Calendar.SECOND)).trim().length() == 2)
			dateStr.append(now.get(Calendar.SECOND));
		else
			dateStr.append("0" + now.get(Calendar.SECOND));
		dateStr.append("Z");
		return dateStr;

	}

}
