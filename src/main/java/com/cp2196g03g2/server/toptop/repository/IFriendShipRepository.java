package com.cp2196g03g2.server.toptop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.FriendShip;

public interface IFriendShipRepository extends JpaRepository<FriendShip, Long>{

	@Query("SELECT f FROM FriendShip f WHERE f.requestUser.id =:requestId AND f.acceptUser.id =:acceptId")
	FriendShip findByRequestIdAndAcceptId(@Param("requestId") String requestId, String acceptId);

	@Query("SELECT COUNT(f.acceptUser.id) FROM FriendShip f WHERE f.requestUser.id=:requestId")
	Long countFollowingByUserId(@Param("requestId") String requestId);
	
	@Query("SELECT COUNT(f.requestUser.id) FROM FriendShip f WHERE f.acceptUser.id=:acceptId")
	Long countFollowerByUserId(@Param("acceptId") String acceptId);

	@Query(value ="SELECT you.request_id FROM tbl_friend_ship AS me " + 
			"INNER JOIN tbl_friend_ship AS you ON me.accept_id = you.request_id " + 
			"WHERE me.request_id = you.accept_id AND me.request_id =:userid", nativeQuery = true)
	ArrayList<String> findAllListFriend(@Param("userid") String userid);

	
	@Query("DELETE FROM FriendShip f WHERE f.requestUser.id=:request_id AND f.acceptUser.id =:acceptId")
	@Modifying
	void deleteByRequestId(@Param("request_id")String requestId, @Param("acceptId")String acceptId);
}
