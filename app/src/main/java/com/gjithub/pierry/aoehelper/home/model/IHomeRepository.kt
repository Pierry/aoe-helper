package com.gjithub.pierry.aoehelper.home.model

import com.gjithub.pierry.aoehelper.core.domain.Civi
import com.gjithub.pierry.aoehelper.core.domain.Last
import com.gjithub.pierry.aoehelper.core.domain.Match
import com.gjithub.pierry.aoehelper.core.domain.User
import kotlinx.coroutines.flow.Flow

interface IHomeRepository  {
    suspend fun search(typed: String): Flow<Result<User>>
    suspend fun match(steamId: String): Flow<Result<Match>>
    suspend fun matches(steamId: String): Flow<Result<List<Last>>>
    suspend fun civis(): Flow<Result<Civi>>
}