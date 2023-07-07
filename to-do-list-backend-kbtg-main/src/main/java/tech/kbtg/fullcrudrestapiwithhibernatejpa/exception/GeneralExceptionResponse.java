package tech.kbtg.fullcrudrestapiwithhibernatejpa.exception;

public class GeneralExceptionResponse
{
	private String message;
	private int statusCode;

	public GeneralExceptionResponse()
	{
	}

	public GeneralExceptionResponse( String message , int statusCode )
	{
		this.message = message;
		this.statusCode = statusCode;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}

	public int getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode( int statusCode )
	{
		this.statusCode = statusCode;
	}
}
