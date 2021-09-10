package com.example.demo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.example.demo.Bulk2ClientBuilder;

import response.CreateJobResponse;
import response.GetJobInfoResponse;
import response.JobInfo;
import type.OperationEnum;

public class RestMy {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("Hello World");
		try {
			Bulk2Client client = new Bulk2ClientBuilder().withPassword(
					"3MVG9z6NAroNkeMlXxQzqzns1y8ezFIDmMTL1pUH0cTPtQCPog2zRrie5y8bYl8XYr1FKCRMpH9KXldLO4NQg",
					"1F880413C2F920B949B248496396C790F415CD94068BC7BB84E89377F8C75490",
					"y.takeuchi@itforce.co.jp.totate.proto",
					"Itforce111").useSandbox().build();


			/**データ登録処理*/
//			CreateJobResponse createJobResponse = client.createJob("Teieigun__c", OperationEnum.INSERT);
//			String jobId = createJobResponse.getId();
//
//			String csv = "Name\n" +
//			        "TestAccount1\n" +
//			        "TestAccount4\n" +
//			        "TestAccount5";
//			client.uploadJobData(jobId, csv);


			
			/**
			 * データUPSERT処理 
			 * 　　データローダのUpsertを使用し、Salesforceにあるレコードで、すでに存在するものは一括で更新、存在しないものは一括登録という処理をおこなう方法
			 * →　マージ処理見たい
			 * */
			
//			CreateJobResponse createJobResponseUpdate = client.createJob("Teieigun__c", OperationEnum.UPSERT);
//			String jobId = createJobResponseUpdate.getId();
//
//			String csvUpdate = "Id,Name\n" +
//			        "a1W6D000000TSuhUAG,TestAccount1\n" +
//			        "a1W6D000000TSuiUAG,TestTeiAccount2\n" +
//			        "a1W6D000000TSujUAG,TestAccount3";
//			client.uploadJobData(jobId, csvUpdate);
			
			/**
			 * データUPSERT処理 → 普通の更新処理
			 * */
//			CreateJobResponse createJobResponseUpdate = client.createJob("Teieigun__c", OperationEnum.UPDATE);
//			String jobId = createJobResponseUpdate.getId();
//
//			String csvUpdate = "Id,Name\n" +
//			        "a1W6D000000TSuhUAG,TestAccount1\n" +
//			        "a1W6D000000TSuiUAG,TestTeiAccount4\n" +
//			        "a1W6D000000TSujUAG,TestAccount3";
//			client.uploadJobData(jobId, csvUpdate);
			
			/**
			 * データ削除処理
			 * */
			
			CreateJobResponse createJobResponseUpdate = client.createJob("Teieigun__c", OperationEnum.DELETE);
			String jobId = createJobResponseUpdate.getId();

			String csvUpdate = "Id\n" +
			        "a1W6D000000TSuhUAG\n" +
			        "a1W6D000000TSuiUAG\n" +
			        "a1W6D000000TSujUAG";
			client.uploadJobData(jobId, csvUpdate);
			
			
			//データ参照
			
			
			// When using a separate request to upload data, make sure to close the job
			JobInfo closeJobResponse = client.closeJob(jobId);

			while (true) {
			    TimeUnit.SECONDS.sleep(1);

			    GetJobInfoResponse jobInfo = client.getJobInfo(jobId);
			    if (jobInfo.isFinished()) {
			        break;
			    }
			}
		
		} catch (IOException e) {				
					
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
