package ch.course223.advanced.domainmodels.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/authorities")
public class AuthorityController {


    private AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('AUTHORITY_SEE')")
    public ResponseEntity<Authority> findById(@PathVariable long id) {
        Authority authority = authorityService.findById(id);
        return new ResponseEntity<>(authority, HttpStatus.OK);
    }

    @GetMapping({"", "/"})
    @PreAuthorize("hasAuthority('AUTHORITY_SEE')")
    public @ResponseBody ResponseEntity<List<Authority>> findAll() {
        List<Authority> authoritys = authorityService.findAll();
        return new ResponseEntity<>(authoritys, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('AUTHORITY_CREATE')")
    public ResponseEntity<Authority> create(@Valid @RequestBody Authority authority) {
        return new ResponseEntity<>(authorityService.save(authority), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('AUTHORITY_MODIFY')")
    public ResponseEntity<Authority> updateById(@PathVariable long id, @Valid @RequestBody Authority authority) {
        return new ResponseEntity<>(authorityService.updateById(id, authority), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('AUTHORITY_DELETE')")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        authorityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
