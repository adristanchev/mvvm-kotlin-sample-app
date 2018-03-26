package com.all6sand7s.kotlinplayground

import com.all6sand7s.kotlinplayground.model.User
import com.all6sand7s.kotlinplayground.network.GithubApi
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by adris on 2/26/2018.
 */
class UserTest {

    @Mock
    val api = Mockito.mock(GithubApi::class.java)

    @Test
    fun testGetUser() {
        val user = User(1, "Test", "email@test.com")
        Mockito.`when`(api.getUser())
                .thenReturn(Observable.just(user))
        api.getUser().subscribe({ t -> assert(t == user) })
    }

    @Test
    fun testGetUsers() {
        val user1 = User(1, "one", "one")
        val user2 = User(2, "two", "two")
        val user3 = User(3, "three", "trhee")
        val users = listOf(user1, user2, user3)

        Mockito.`when`(api.getUsers())
                .thenReturn(Observable.fromArray(users))

        api.getUsers().subscribe({ t ->
            assert(t.size == 3)
            assert(t[0].id == 1) })
    }
}