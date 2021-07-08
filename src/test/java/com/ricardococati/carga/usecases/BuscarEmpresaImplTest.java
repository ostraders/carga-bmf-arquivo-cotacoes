package com.ricardococati.carga.usecases;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_001;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_002;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_003;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_004;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_005;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_006;
import static com.ricardococati.carga.templates.EmpresaDTOTemplateLoader.EMPRESA_DTO_VALID_007;
import static com.ricardococati.carga.templates.EmpresaTemplateLoader.EMPRESA_VALID_001;
import static com.ricardococati.carga.templates.EmpresaTemplateLoader.EMPRESA_VALID_002;
import static com.ricardococati.carga.templates.EmpresaTemplateLoader.EMPRESA_VALID_003;
import static com.ricardococati.carga.templates.EmpresaTemplateLoader.EMPRESA_VALID_004;
import static com.ricardococati.carga.templates.EmpresaTemplateLoader.EMPRESA_VALID_005;
import static com.ricardococati.carga.templates.EmpresaTemplateLoader.EMPRESA_VALID_006;
import static com.ricardococati.carga.templates.EmpresaTemplateLoader.EMPRESA_VALID_007;
import static com.ricardococati.carga.templates.HeaderDTOTemplateLoader.HEADER_DTO_VALID_001;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.carga.adapters.repositories.empresa.EmpresaBuscarDAO;
import com.ricardococati.carga.entities.domains.empresa.Empresa;
import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import com.ricardococati.carga.entities.domains.header.dto.HeaderDTO;
import com.ricardococati.carga.usecases.empresa.impl.BuscarEmpresaImpl;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarEmpresaImplTest {

  @InjectMocks
  private BuscarEmpresaImpl target;
  @Mock
  private EmpresaBuscarDAO empresaBuscarDAO;

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.carga.templates");
  }

  @Test
  public void testBuscarPorNomeEmpresa() throws Exception {
//    //given
//    //when(empresaBuscarDAO.buscaEmpresasPorNome(anyInt(), anyLong())).thenReturn(empresaList());
//    when(empresaBuscarDAO.buscaEmpresasPorNome(anyString(), anyInt(), anyLong())).thenReturn(empresaList());
//    //when
//    //then
  }

  private Empresa buildEmpresa(){
    return from(Empresa.class).gimme(EMPRESA_DTO_VALID_001);
  }

  private List<Empresa> empresaList(){
    return from(Empresa.class)
        .gimme(7,
            EMPRESA_VALID_001,
            EMPRESA_VALID_002,
            EMPRESA_VALID_003,
            EMPRESA_VALID_004,
            EMPRESA_VALID_005,
            EMPRESA_VALID_006,
            EMPRESA_VALID_007
        );
  }
}