

//TODO List

/*   1. check for
context for showing error:- -> we need activity context for show and snackbar -> check for custom
snackbar UI with image loading in it using glide?:Default

2. title, image, type of error to shown and isShow(to show or not) come from the backend in error
   response
3. If error_type not come from backend the show snackbar(default one)
4. if image_url not come from backend then show default one
5. if title not come from backend then show default one
6. if is_show not come from backend the is_Show will be true and if false then we don't show the
   error on UI
7. Client will able handle some action with snackbar 8.IMPORTANT NOT: When we got exception it will
   not give us error_response instead it will give the exception then we have to show default
   Snackbar with message
9. Also design custom UI for Snackbars

   TODO Important
    1. Change ErrorResponse data class because it will contain (type_of_error, is_shown,
       image_url,error_message)
    2. data class ErrorResponse(
       val responseCode: Int? = 0, val responseErrorBody: ResponseBody? = null, val data:
       DataStatus, val exception: Throwable? = null, val isShow: Boolean = true
       )
       
  3. think to change this above data class with more param or add one more data class for
   ErrorResponseFromBackend(which come from the backend)

* */

//not use type here on client side because the type will come from backend in the error response
//and if we got the exception show snack bar with message //also design the custom UI for snack bar
and toast

//I need body, response code and Throwable /*

* client will pass these above three param in Resource.Error(body,response code,throwable/exception)
*
* Then from library I will check which kind of error it is
*
* .1 if body is null then we can check what kind of error it is
* .2 Client will use our getResultMethod to call api
* .3 Client will also be using our Resource file to ge result
*
* */

//sealed class HttpErrors<out T> : Resource<T>() { // data class ResourceForbidden<T>(val exception:
T) : HttpErrors<T>()
// data class ResourceNotFound<T>(val exception: T) : HttpErrors<T>()
// data class InternalServerError<T>(val exception: T) : HttpErrors<T>()
// data class BadGateWay<T>(val exception: T) : HttpErrors<T>()
// data class ResourceRemoved<T>(val exception: T) : HttpErrors<T>()
// data class RemovedResourceFound<T>(val exception: T) : HttpErrors<T>()
// data class InvalidData<T>(val exception: T) : HttpErrors<T>()
// data class NetworkException<T>(val exception: T) : HttpErrors<T>()
//}