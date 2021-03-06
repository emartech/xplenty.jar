/**
 * 
 */
package com.xplenty.api;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * A bunch of convenience structures
 * @author Yuriy Kovalek
 *
 */
@SuppressWarnings("unused")
public class Xplenty {
    public static final int MAX_LIMIT = 100;

	public enum Sort {
		updated("updated"),
		created("created");
		
		public final String value;
		
		Sort(String value) {
			this.value = value;
		}
		
		public String toString() {
			return value;
		}
	}
	
	public enum SortDirection {
		ascending("asc"),
		descending("desc");
		
		public final String value;
		
		SortDirection(String value) {
			this.value = value;
		}
		
		public String toString() {
			return value;
		}
	}
	
	public enum JobStatus {		
		idle("idle"),
		stopped("stopped"),
		completed("completed"),
		pending("pending"),
		failed("failed"),
		running("running"),
		pending_stoppage("pending_stoppage"),
		stopping("stopping");
		
		@SuppressWarnings("unused")
		private final String status;
		
		JobStatus(String status) {
			this.status = status;
		}

	}

    public enum ScheduleStatus {
        enabled("enabled"),
        disabled("disabled"),
        /**
         * used only for filtering meaning all the statuses above
         */
        all("all")
        ;
        @SuppressWarnings("unused")
        private final String status;

        ScheduleStatus(String status) {
            this.status = status;
        }
    }
    
    public enum ScheduleIntervalUnit {

        minutes("minutes"),
        hours("hours"),
        days("days"),
        weeks("weeks"),
        months("months");

        @SuppressWarnings("unused")
        private final String unit;

        ScheduleIntervalUnit(String unit) {
            this.unit = unit;
        }
    }
	
	public enum ClusterType {		
		production("production"),
		sandbox("sandbox");
		
		@SuppressWarnings("unused")
		private final String type;
		
		ClusterType(String type) {
			this.type = type;
		}

	}

	@JsonFormat(shape= JsonFormat.Shape.SCALAR)
	public static enum ClusterStatus {
		pending("pending"),
		error("error"),
		creating("creating"),
		available("available"),
		scaling("scaling"),
		pending_terminate("pending_terminate"),
		terminating("terminating"),
		terminated("terminated");
		
		@SuppressWarnings("unused")
		private final String status;
		
		ClusterStatus(String status) {
			this.status = status;
		}
	}

	/**
	 * Endpoints and short descriptions for REST resources
	 */
	public static enum Resource {
        Package("packages/%s", "Get package info"),
        Packages("packages", "List packages"),
		ClusterPlans("cluster_plans", "List cluster plans"),
		Clusters("clusters", "List clusters"), 
		Cluster("clusters/%s", "Get cluster information"),
		CreateCluster("clusters", "Create cluster"),
		UpdateCluster("clusters/%s", "Update cluster"),
		TerminateCluster("clusters/%s", "Terminate cluster"),
		Jobs("jobs", "List jobs"),
		Job("jobs/%s", "Get job info"),
		RunJob("jobs", "Run job"),
		StopJob("jobs/%s", "Stop job"),
        ClusterWatcher("clusters/%s/watchers", "adding/removing a cluster watchers"),
        JobWatcher("jobs/%s/watchers", "adding/removing a job watchers"),
        Schedules("schedules", "list schedules"),
        CreateSchedule("schedules", "Create schedule"),
        CloneSchedule("schedules/%s/clone", "Clone schedule"),
        UpdateSchedule("schedules/%s", "Update schedule"),
        RemoveSchedule("schedules/%s", "Remove schedule"),
        Schedule("schedules/%s", "Get schedule information")
        ;
		
		public final String value;
		public final String name;
		
		Resource(String val, String name) {
			this.value = val;
			this.name = name;
		}
		
		public String format(String... values) {
			return String.format(value, (Object[]) values);
		}
	}
	
	public static enum Version {
		V1(1);
		
		private final int value;
		
		Version(int ver) {
			this.value = ver;
		}
		
		public String format() {
			return "version=" + Integer.toString(value);
		}
	}

    public static enum SubjectType {
        CLUSTER, JOB
    }
}
