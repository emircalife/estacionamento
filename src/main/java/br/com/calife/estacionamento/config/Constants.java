package br.com.calife.estacionamento.config;

import java.util.Locale;

public interface Constants {
    /*
     *  CONTROLLERS
     */
    public interface CONTROLLER {
        /*	*/
        public static final String BASE_URL = "";
        public static final String SLASH = "/";
        public static final String BUILD = "build";

        /*	*/
        public interface SAIDA {
            public static final String BASE_URL = "/saida";
        }

        /*	*/
        public interface ENTRADA {
            public static final String BASE_URL = "/entrada";
        }
    }

    interface TIME_ZONE {
        public static final String DEFAULT = "America/Sao_Paulo";
    }

    interface LOCALE {
        public static final Locale DEFAULT = new Locale("pt", "BR");
        public static final Locale SQL = new Locale("en", "US");
    }

    interface JSON {
        interface DATE {
            public static final String DEFAULT = "yyyy-MM-dd";
            public static final String SMMALDATETIME = "yyyy-MM-dd HH:mm";
            public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
            public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS"; // "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
            public static final String TIME = "HH:mm:ss";
        }
    }

    interface DOCUMENTATION {
        interface MESSAGES {
            /*
             * CRUD DOCUMENTATION MESSAGES CONSTANTS
             */
            // SUMMARIES
            public static final String SUMMARY_CREATE = "Cadastrar novo registro";
            public static final String SUMMARY_FIND_BY_ID = "Pesquisar registro por ID";
            public static final String SUMMARY_FIND_ALL = "Localizar todos os recursos";
            public static final String SUMMARY_UPDATE = "Recurso para atualizar registro";
            public static final String SUMMARY_DELETE = "Recurso para deletar registro";
            // SUMMARIES
            public static final String SUMMARY_ENTRADA = "Cadastrar nova entrada";
            public static final String SUMMARY_SAIDA = "Cadastrar nova saida";
            // RESPONSES
            public static final String RESPONSE_201 = "Recurso criado com sucesso";
            public static final String RESPONSE_200_UPDATE = "Registro atualizado com sucesso.";
            public static final String RESPONSE_200_FIND_ALL = "Registros localizados com sucesso.";
            public static final String RESPONSE_200_FIND_BY_ID = "Registros localizados com sucesso.";
            public static final String RESPONSE_204 = "Registro deletado com sucesso.";

            // ERRORS
            public static final String ERROR_403 = "Recurso n\u00E3o permitido ao seu perfil.";
            public static final String ERROR_404 = "Recurso n\u00E3o encontrado";
            public static final String ERROR_409 = "Recurso j\u00E1 registrado no sistema";
            public static final String ERROR_422 = "Recurso n\u00E3o processado por falta de dados ou dados inv\u00E1lidos";
        }
    }

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
}
