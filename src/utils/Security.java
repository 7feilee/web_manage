package utils;

import org.jetbrains.annotations.Nullable;
public class Security
{
	@Nullable
	public static String MD5(String md5)
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte anArray : array)
			{
				sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}
		catch (java.security.NoSuchAlgorithmException e)
		{
			return null;
		}
	}
}
