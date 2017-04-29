package education.cs.scu.checkInformation;

/**
 * Created by maicius on 2017/4/22.
 */
public interface CheckInformation {
    boolean whetherUser(String userName) throws Exception;
    boolean whetherFriends(String userName, String friendName) throws Exception;
}
