package com.eprogramar.bank.controller

import com.eprogramar.bank.model.Account
import com.eprogramar.bank.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(private val service: AccountService) {

    @CrossOrigin()
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody account: Account): Account = service.create(account)

    @CrossOrigin()
    @GetMapping
    fun getAll(): List<Account> = service.getAll()

    @CrossOrigin()
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<Account> =
            service.getById(id).map {
                ResponseEntity.ok(it)
            }.orElse(ResponseEntity.notFound().build())

    @CrossOrigin()
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody account: Account) : ResponseEntity<Account> =
            service.update(id, account).map {
                ResponseEntity.ok(it)
            }.orElse(ResponseEntity.notFound().build())

    @CrossOrigin()
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}