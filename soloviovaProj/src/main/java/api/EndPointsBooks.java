package api;

public interface EndPointsBooks {
    String baseUrl = "https://demoqa.com";
    String loginBookstore = baseUrl +"/Account/v1/Login";
  //  String deleteBooks = baseUrl + "/BookStore/v1/Books?UserId={0}";
    String bookStore = baseUrl + "/BookStore/v1/Books";
    String userAccount = baseUrl + "/Account/v1/User/{0}";
}

