package fr.nikho.epitech.intra;

import fr.nikho.epitech.intra.data.Activity;
import fr.nikho.epitech.intra.data.Dashboard;
import fr.nikho.epitech.intra.data.Document;
import fr.nikho.epitech.intra.data.Marks;
import fr.nikho.epitech.intra.data.MissedCourses;
import fr.nikho.epitech.intra.data.Module;
import fr.nikho.epitech.intra.data.Notification;
import fr.nikho.epitech.intra.data.PlanningActivity;
import fr.nikho.epitech.intra.data.Project;
import fr.nikho.epitech.intra.data.User;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface EpitechClient {

    @Headers("Accept: application/json")
    @GET("?format=json")
    Observable<Dashboard> getDashboard();

    @Headers("Accept: application/json")
    @GET("user/?format=json")
    Observable<User> getAuthUser();

    @Headers("Accept: application/json")
    @GET("user/{email}/binome/?format=json")
    Observable<User.UserBinomes> getBinomes(
            @Path("email") String email
    );

    @Headers("Accept: application/json")
    @GET("planning/load?format=json")
    Call<PlanningActivity[]> getPlanningActivities();

    @Headers("Accept: application/json")
    @GET("module/{year}/{module}/{instance}/{activity}/?format=json")
    Call<Activity> getActivity(
            @Path("year") String year,
            @Path("module") String module,
            @Path("instance") String instance,
            @Path("activity") String activity
    );

    @Headers("Accept: application/json")
    @GET("module/{year}/{module}/{instance}/?format=json")
    Observable<Module> getModule(
            @Path("year") String year,
            @Path("module") String module,
            @Path("instance") String instance
    );

    @Headers("Accept: application/json")
    @GET("module/{year}/{module}/{instance}/registered/?format=json")
    Call<Module.StudentRegistered> getStudentRegisteredModule(
            @Path("year") String year,
            @Path("module") String module,
            @Path("instance") String instance
    );

    @Headers("Accept: application/json")
    @GET("module/{year}/{module}/{instance}/{activity}/project/?format=json")
    Call<Project> getProject(
            @Path("year") String year,
            @Path("module") String module,
            @Path("instance") String instance,
            @Path("activity") String activity
    );

    @Headers("Accept: application/json")
    @POST("module/{year}/{module}/{instance}/{activity}/{event}/register?format=json")
    Call<String> registerToAEvent(
            @Path("year") String year,
            @Path("module") String module,
            @Path("instance") String instance,
            @Path("activity") String activity,
            @Path("event") String event
    );
    @Headers("Accept: application/json")
    @POST("module/{year}/{module}/{instance}/{activity}/{event}/unregister?format=json")
    Call<String> unregisterToAEvent(
            @Path("year") String year,
            @Path("module") String module,
            @Path("instance") String instance,
            @Path("activity") String activity,
            @Path("event") String event
    );

    @Headers("Accept: application/json")
    @GET("module/2020/B-INN-000/FR-0-1/?format=json")
    Observable<Module> getHubFRModule();

    @Headers("Accept: application/json")
    @GET("module/2020/B-INN-000/NAN-0-1/?format=json")
    Observable<Module> getHubModule();

    @Headers("Accept: application/json")
    @GET("user/{email}/notification/message?format=json")
    Observable<Notification[]> getNotifications(
            @Path("email") String email
    );

    @Headers("Accept: application/json")
    @GET("user/{email}/notes/?format=json")
    Observable<Marks> getMarks(
            @Path("email") String email
    );

    @Headers("Accept: application/json")
    @GET("user/{email}/document/{file}")
    @Streaming
    Observable<ResponseBody> downloadDocument(
            @Path("email") String email,
            @Path("file") String file
    );

    @Headers("Accept: application/json")
    @GET("user/{email}/document/?format=json")
    Observable<Document[]> getDocuments(
            @Path("email") String email
    );

    @Headers("Accept: application/json")
    @GET("user/{email}/notifications/missed/?format=json")
    Observable<MissedCourses> getMissedCourses(
            @Path("email") String email
    );

    @Headers("Accept: application/json")
    @GET("user/{email}/netsoul/?format=json")
    Observable<Double[][]> getNetlog(
            @Path("email") String email
    );

/*    @Headers("Accept: application/json")
    @GET("user/{email}/notes/?format=json")
    Call<Notes[]> getAuthUserNotes(
            @Path("email") String email
    );*/

    @Headers("Accept: application/json")
    @GET("user/{email}/flags/?format=json")
    Observable<Response<User.Flags>> getUserFlags(
            @Path("email") String email
    );


}
