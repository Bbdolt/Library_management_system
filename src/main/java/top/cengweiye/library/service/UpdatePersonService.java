package top.cengweiye.library.service;

import top.cengweiye.library.domain.Reader;

import javax.servlet.http.HttpServletRequest;

public interface UpdatePersonService {
    public String updatePerson(Reader reader, String token);
}
