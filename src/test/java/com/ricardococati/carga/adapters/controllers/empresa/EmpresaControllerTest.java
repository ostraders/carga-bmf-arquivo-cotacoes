package com.ricardococati.carga.adapters.controllers.empresa;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_001;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_002;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_003;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_004;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_005;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_006;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_007;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import com.ricardococati.carga.entities.domains.empresa.EmpresaRequest;
import com.ricardococati.carga.usecases.empresa.BuscarEmpresa;
import com.ricardococati.carga.usecases.empresa.InserirEmpresa;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {EmpresaController.class})
@AutoConfigureMockMvc(addFilters = false)
public class EmpresaControllerTest {

  @MockBean
  private InserirEmpresa inserirEmpresa;
  @MockBean
  private BuscarEmpresa buscarEmpresa;
  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @Before
  public void setUp(){
    FixtureFactoryLoader.loadTemplates("com.ricardococati.carga.templates");
    this.objectMapper = new ObjectMapper();
  }

  @Test
  public void testCriaEmpresas() throws Exception {
    //given
    EmpresaRequest empRequest = getEmpresaRequest();
    //when
    final ResultActions result = this.mockMvc
        .perform(
            post(String.format("/api/v1/empresa"))
                .content(objectMapper.writeValueAsString(empRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    //then
    result
        .andExpect(status().isNoContent());
  }

  @Test
  public void testBuscarEmpresas() {
  }

  private EmpresaRequest getEmpresaRequest(){
    return new EmpresaRequest(empresaDTOList());
  }

  private List<EmpresaDTO> empresaDTOList(){
    return from(EmpresaDTO.class)
        .gimme(7,
            EMPRESA_DTO_VALID_001,
            EMPRESA_DTO_VALID_002,
            EMPRESA_DTO_VALID_003,
            EMPRESA_DTO_VALID_004,
            EMPRESA_DTO_VALID_005,
            EMPRESA_DTO_VALID_006,
            EMPRESA_DTO_VALID_007
        );
  }

}