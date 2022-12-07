package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.utils.PAInput;

public class AppUI {
    AppContext fsm;
    AppManager app;

    public AppUI(AppContext fsm, AppManager app) {
        this.fsm = fsm;
        this.app = app;
    }

    boolean finish;

    public void start(){
        while (!finish){
            switch (fsm.getState()){
                case FASE1 -> fase1UI();
                case FASE1FECHADA -> fase1FechadaUI();
                case GESTAO_ALUNOS -> gestaoAlunosUI();
                case GESTAO_DOCENTES -> gestaoDocentesUI();
                case GESTAO_PROPOSTAS-> gestaoPropostasUI();
                case FASE2 -> fase2UI();
                case FASE2FECHADA -> fase2FechadaUI();
                case GESTAO_CANDIDATURAS -> gestaoCandidaturasUI();
                case FASE3 -> fase3UI();
                case DECIDEEMPATE -> decideEmpateUI();
                case ATRIBUICAOMANUAL -> atribuicaoManualUI();
                case FASE3FECHADA -> fase3FechadaUI();
                case FASE4 -> fase4UI();
                case GESTAO_ORIENTADORES -> gestaoOrientadoresUI();
                case ESPERAESCOLHA -> esperaEscolhaUI();
                case FASE5 -> fase5UI();
            }
        }
    }

    private void fase1UI() {
        System.out.println("*** CONFIGURAÇÃO ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Gestão de Alunos", "Gestão de Docentes", "Gestão de Propostas", "Carregar Dados Anteriores", "Salvar Dados Atuais", "Fechar Fase", "Opções de Candidatura", "Sair")){
            case 1 -> fsm.alunos();
            case 2 -> fsm.docentes();
            case 3 -> fsm.propostas();
            case 4 -> {
                if(app.load())
                    System.out.println("Dados carregados com sucesso!");
                else
                    System.out.println("Erro ao carregar os dados!");
            }
            case 5 -> {
                if(app.save())
                    System.out.println("Dados guardados com sucesso!");
                else
                    System.out.println("Erro ao guardar os dados!");
            }
            case 6 ->{
                if(fsm.fechaFase())
                    System.out.println("Fase fechada com sucesso!");
                else
                    System.out.println("Impossibilidade de fechar a fase!");
            }
            case 7 -> fsm.fase2();
            default -> finish = true;
        }
    }

    private void fase1FechadaUI() {
        System.out.println("*** CONFIGURAÇÃO (FECHADO) ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Listar Alunos", "Listar Docentes", "Listar Propostas", "Opções de Candidatura", "Sair")) {
            case 1 -> System.out.println(fsm.listarAlunos());
            case 2 -> System.out.println(fsm.listarDocentes());
            case 3 -> System.out.println(fsm.listarPropostas());
            case 4 -> fsm.fase2();
            default -> finish = true;
        }
    }

    private void gestaoAlunosUI() {
        System.out.println("*** Gestão de Alunos ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Adicionar alunos", "Eliminar Aluno", "Listar alunos", "Exportar dados para ficheiro CSV", "Voltar atrás")){
            case 1 -> {
                int valor = PAInput.chooseOption("Escolha o modo de adição: ", "Adicionar por ficheiro CSV", "Adicionar por teclado", "Voltar atrás");
                if(valor == 1){
                    String fich = PAInput.readString("Introduza o nome do ficheiro: ", false);
                    fsm.add(fich);
                }else if(valor == 2)
                    System.out.println("Por implementar!");
                else
                    fsm.alunos();
            }
            case 2 -> {
                long nr = PAInput.readInt("Introduza o número de aluno que pertence eliminar: ");
                if(fsm.eliminarAluno(nr))
                    System.out.println("Aluno eliminado com sucesso!\n");
                else
                    System.out.println("Erro na eliminação do aluno!\n");
            }
            case 3 -> System.out.println(fsm.listar());
            case 4 ->{
                String nome = PAInput.readString("Introduza o nome do ficheiro: ", false);
                if(fsm.escreverAlunos(nome))
                    System.out.println("Dados exportados com sucesso!\n");
                else
                    System.out.println("Erro na exportação dos dados\n");
            }
            case 5 -> fsm.fase1();
        }
    }

    private void gestaoDocentesUI() {
        System.out.println("*** Gestão de Docentes ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Adicionar docentes", "Eliminar Docente", "Listar docentes", "Exportar dados para ficheiro CSV", "Voltar atrás")){
            case 1 -> {
                int valor = PAInput.chooseOption("Escolha o modo de adição: ", "Adicionar por ficheiro CSV", "Adicionar por teclado", "Voltar atrás");
                if(valor == 1){
                    String fich = PAInput.readString("Introduza o nome do ficheiro: ", false);
                    fsm.add(fich);
                }else if(valor == 2)
                    System.out.println("Por implementar!");
                else
                    fsm.docentes();
            }
            case 2 -> {
                String email = PAInput.readString("Introduza o e-mail do docente que pretende eliminar: ", false);
                if(fsm.eliminarDocente(email))
                    System.out.println("Docente eliminado com sucesso!\n");
                else
                    System.out.println("Erro na eliminação do docente!\n");
            }
            case 3 -> System.out.println(fsm.listar());
            case 4 -> {
                String nome = PAInput.readString("Introduza o nome do ficheiro: ", false);
                if(fsm.escreverDocentes(nome))
                    System.out.println("Dados exportados com sucesso!\n");
                else
                    System.out.println("Erro na exportação dos dados\n");
            }
            case 5 -> fsm.fase1();
        }
    }

    private void gestaoPropostasUI() {
        System.out.println("*** Gestão de Propostas ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Adicionar propostas", "Eliminar Proposta", "Listar propostas", "Exportar dados para ficheiro CSV", "Voltar atrás")){
            case 1 -> {
                int valor = PAInput.chooseOption("Escolha o modo de adição: ", "Adicionar por ficheiro CSV", "Adicionar por teclado", "Voltar atrás");
                if(valor == 1){
                    String fich = PAInput.readString("Introduza o nome do ficheiro: ", false);
                    fsm.add(fich);
                }else if(valor == 2)
                    System.out.println("Por implementar!");
                else
                    fsm.propostas();
            }
            case 2->{
                String id = PAInput.readString("Introduza o código da proposta que pretende eliminar: ", false);
                if(fsm.eliminarProposta(id))
                    System.out.println("Proposta eliminada com sucesso!\n");
                else
                    System.out.println("Erro na eliminação da proposta!\n");
            }
            case 3 -> System.out.println(fsm.listar());
            case 4 -> {
                String nome = PAInput.readString("Introduza o nome do ficheiro: ", false);
                if(fsm.escreverPropostas(nome))
                    System.out.println("Dados exportados com sucesso!\n");
                else
                    System.out.println("Erro na exportação dos dados\n");
            }
            case 5 -> fsm.fase1();
        }
    }

    private void fase2UI() {
        System.out.println("*** Opções de Candidatura ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Configurar Candidaturas", "Listar Alunos", "Listar Propostas", "Exportar dados para ficheiro CSV", "Fechar Fase",  "Configuração", "Atribuição de Propostas", "Sair")){
            case 1 -> fsm.candidaturas();
            case 2 -> {
                switch(PAInput.chooseOption("Escolha uma opção: ", "Alunos com Autoproposta", "Alunos com Candidatura", "Alunos sem Candidatura", "Voltar atrás")) {
                    case 1 -> System.out.println(fsm.listarAlunosAutoproposta());
                    case 2 -> System.out.println(fsm.listarAlunosComCand());
                    case 3 -> System.out.println(fsm.listarAlunosSemCand());
                }
            }
            case 3 -> {
                StringBuilder sb = new StringBuilder();
                int[] valores = new int[5];
                int i = 0;
                System.out.println("\nEscolha uma ou mais opções:\n\n 1 - Autopropostas de Alunos\n 2 - Propostas de Docentes\n 3 - Propostas com Candidaturas\n 4 - Propostas sem Candidaturas\n 0 - Mostrar resultados\n");

                do {
                    valores[i] = PAInput.readInt("Introduza um valor: ");
                    i++;
                }while (valores[i-1] != 0);

                int j = 0;

                do {
                    switch (valores[j]) {
                        case 1 -> sb.append(fsm.listaAutopropostas());
                        case 2 -> sb.append(fsm.listaPropDocentes());
                        case 3 -> sb.append(fsm.listarPropComCand());
                        case 4 -> sb.append(fsm.listarPropSemCand());
                    }
                    j++;
                }while (j <= i);
                System.out.println(sb);
            }
            case 4 -> {
                String nome = PAInput.readString("Introduza o nome do ficheiro: ", false);
                if(fsm.escreverCandidaturas(nome))
                    System.out.println("Dados exportados com sucesso!\n");
                else
                    System.out.println("Erro na exportação dos dados\n");
            }
            case 5 ->{
                if(fsm.fechaFase())
                    System.out.println("Fase fechada com sucesso!");
                else
                    System.out.println("Impossibilidade de fechar a fase!");
            }
            case 6 -> fsm.fase1();
            case 7 -> fsm.fase3();
            default -> finish = true;
        }
    }

    private void fase2FechadaUI() {
        System.out.println("*** Opções de Candidatura (FECHADO) ***");
        switch (PAInput.chooseOption("Escolha uma opção:",  "Listar Candidaturas", "Listar Alunos", "Listar Propostas", "Configuração", "Atribuição de Propostas", "Sair")){
            case 1 -> System.out.println(fsm.listarCandidaturas());
            case 2 -> {
                switch(PAInput.chooseOption("Escolha uma opção: ", "Alunos com Autoproposta", "Alunos com Candidatura", "Alunos sem Candidatura", "Voltar atrás")) {
                    case 1 -> System.out.println(fsm.listarAlunosAutoproposta());
                    case 2 -> System.out.println(fsm.listarAlunosComCand());
                    case 3 -> System.out.println(fsm.listarAlunosSemCand());
                }
            }
            case 3 -> {
                StringBuilder sb = new StringBuilder();
                int[] valores = new int[5];
                int i = 0;
                System.out.println("\nEscolha uma ou mais opções:\n\n 1 - Autopropostas de Alunos\n 2 - Propostas de Docentes\n 3 - Propostas com Candidaturas\n 4 - Propostas sem Candidaturas\n 0 - Mostrar resultados\n");

                do {
                    valores[i] = PAInput.readInt("Introduza um valor: ");
                    i++;
                }while (valores[i-1] != 0);

                int j = 0;

                do {
                    switch (valores[j]) {
                        case 1 -> sb.append(fsm.listaAutopropostas());
                        case 2 -> sb.append(fsm.listaPropDocentes());
                        case 3 -> sb.append(fsm.listarPropComCand());
                        case 4 -> sb.append(fsm.listarPropSemCand());
                    }
                    j++;
                }while (j <= i);
                System.out.println(sb);
            }
            case 4 -> fsm.fase1();
            case 5 -> fsm.fase3();
            default -> finish = true;
        }
    }

    private void gestaoCandidaturasUI() {
        System.out.println("*** Gestão de Candidaturas ***");
        switch (PAInput.chooseOption("Escolha uma opção: ", "Adicionar Candidaturas", "Eliminar Candidatura", "Listar Candidaturas", "Voltar atrás")) {
            case 1 -> {
                String fich = PAInput.readString("Introduza o nome do ficheiro: ", false);
                fsm.add(fich);
            }
            case 2 -> {
                long nr = PAInput.readInt("Introduza o número de aluno que pertence eliminar: ");
                if(fsm.eliminarCandidatura(nr))
                    System.out.println("Candidatura eliminada com sucesso!\n");
                else
                    System.out.println("Erro na eliminação da candidatura!\n");
            }
            case 3 -> System.out.println(fsm.listar());
            case 4 -> fsm.fase2();
        }
    }

    private void fase3UI() {
        System.out.println("*** Atribuição de Propostas ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Atribuir automaticamente autopropostas e propostas de docentes", "Atribuir automaticamente propostas", "Atribuir manualmente propostas", "Remover proposta", "Remover todas as propostas possíveis", "Listar Propostas", "Listar Alunos", "Exportar dados para ficheiro CSV", "Fechar Fase", "Opções de Candidatura", "Atribuição de Orientadores", "Sair")){
            case 1 -> fsm.atribuicaoAutoPropostasEDocentes();
            case 2 -> {
                if(!fsm.atribuicaoPropostas()) {
                    fsm.decideEmpate();
                }
            }
            case 3 -> fsm.atribuicaoManual();
            case 4 -> {
                System.out.println(fsm.listaRemocaoManualPropostas());
                int i = PAInput.readInt("Introduza o número do par que deseja remover: ");
                if(fsm.remocaoManualPropostas(i))
                    System.out.println("\nAssociação Aluno-Proposta eliminada com sucesso!");
                else
                    System.out.println("\nFalha na remoção!");
            }
            case 5 ->{
                if(fsm.remocaoTodasPropostas())
                    System.out.println("Eliminações efetuadas com sucesso!");
                else
                    System.out.println("Erro na eliminação!");
                //app.undo();
            }

            case 6 -> {
                StringBuilder sb = new StringBuilder();
                int[] valores = new int[5];
                int i = 0;
                System.out.println("\nEscolha uma ou mais opções:\n\n 1 - Autopropostas de Alunos\n 2 - Propostas de Docentes\n 3 - Propostas Atribuídas\n 4 - Propostas Não Atribuídas\n 0 - Mostrar resultados\n");

                do {
                    valores[i] = PAInput.readInt("Introduza um valor: ");
                    i++;
                }while (valores[i-1] != 0);

                int j = 0;

                do {
                    switch (valores[j]) {
                        case 1 -> sb.append(fsm.listaAutopropostas());
                        case 2 -> sb.append(fsm.listaPropDocentes());
                        case 3 -> sb.append(fsm.listarPropAtribuidas());
                        case 4 -> sb.append(fsm.listarPropNaoAtribuidas());
                    }
                    j++;
                }while (j <= i);
                System.out.println(sb);
            }
            case 7 -> {
                switch(PAInput.chooseOption("Escolha uma opção: ", "Alunos com Autoproposta", "Alunos com Candidatura", "Alunos com Propostas Atribuídas", "Alunos sem Propostas Atribuídas", "Voltar atrás")) {
                    case 1 -> System.out.println(fsm.listarAlunosAutoproposta());
                    case 2 -> System.out.println(fsm.listarAlunosComCand());
                    case 3 -> System.out.println(fsm.listarAlunosComPropAtribuida());
                    case 4 -> System.out.println(fsm.listarAlunosSemPropAtribuida());
                }
            }
            case 8 -> {
                String nome = PAInput.readString("Introduza o nome do ficheiro: ", false);
                if(fsm.exportarDadosFase3(nome))
                    System.out.println("Dados exportados com sucesso!\n");
                else
                    System.out.println("Erro na exportação dos dados\n");
            }
            case 9 -> {
                if(fsm.fechaFase())
                    System.out.println("Fase fechada com sucesso!");
                else
                    System.out.println("Impossibilidade de fechar a fase!");
            }
            case 10 -> fsm.fase2();
            case 11 -> fsm.fase4();
            default -> finish = true;
        }
    }

    private void decideEmpateUI() {
        System.out.println("*** Situação de Empate ***");
        System.out.println(fsm.listaAlunosEmpate());

        int aluno = PAInput.readInt("Introduza o número do aluno: ");
        int proposta = PAInput.readInt("Introduza o número do proposta: ");

        fsm.atribuicaodecideEmpate(aluno, proposta);

        fsm.atribuicaoPropostas();
    }

    private void atribuicaoManualUI() {
        System.out.println("\n*** Atribuição de Aluno a Proposta ***");

        int aluno, propIndex, i = 0, j = 0;

        String val = fsm.listaAtribuicaoManualPropostas();
        if(val.equals("")){
            System.out.println("\nNão é possível fazer atribuições!");
            fsm.fase3();
        }else{
            System.out.println(val);
            aluno = PAInput.readInt("Introduza o número do aluno: ");

            propIndex = PAInput.readInt("Introduza o número da proposta: ");

            if (fsm.atribuicaoManualPropostas(propIndex, aluno))
                System.out.println("\nAluno atribuído com sucesso!");
            else
                System.out.println("\nFalha na atribuição!");
        }
    }

    private void fase3FechadaUI() {
        System.out.println("*** Atribuição de Propostas (FECHADO) ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Listar Propostas", "Listar Alunos", "Opções de Candidatura", "Atribuição de Orientadores", "Sair")) {
            case 1 -> {
                StringBuilder sb = new StringBuilder();
                int[] valores = new int[5];
                int i = 0;
                System.out.println("\nEscolha uma ou mais opções:\n\n 1 - Autopropostas de Alunos\n 2 - Propostas de Docentes\n 3 - Propostas Atribuídas\n 4 - Propostas Não Atribuídas\n 0 - Mostrar resultados\n");

                do {
                    valores[i] = PAInput.readInt("Introduza um valor: ");
                    i++;
                } while (valores[i - 1] != 0);

                int j = 0;

                do {
                    switch (valores[j]) {
                        case 1 -> sb.append(fsm.listaAutopropostas());
                        case 2 -> sb.append(fsm.listaPropDocentes());
                        case 3 -> sb.append(fsm.listarPropAtribuidas());
                        case 4 -> sb.append(fsm.listarPropNaoAtribuidas());
                    }
                    j++;
                } while (j <= i);
                System.out.println(sb);
            }
            case 2 -> {
                switch (PAInput.chooseOption("Escolha uma opção: ", "Alunos com Autoproposta", "Alunos com Candidatura", "Alunos com Propostas Atribuídas", "Alunos sem Propostas Atribuídas", "Voltar atrás")) {
                    case 1 -> System.out.println(fsm.listarAlunosAutoproposta());
                    case 2 -> System.out.println(fsm.listarAlunosComCand());
                    case 3 -> System.out.println(fsm.listarAlunosComPropAtribuida());
                    case 4 -> System.out.println(fsm.listarAlunosSemPropAtribuida());
                }
            }
            case 3 -> fsm.fase2();
            case 4 -> fsm.fase4();
            default -> finish = true;
        }
    }

    private void fase4UI() {
        System.out.println("*** Atribuição de Orientadores ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Atribuir automaticamente docentes", "Configurar orientadores", "Listar Dados", "Exportar dados para ficheiro CSV", "Fechar Fase", "Atribuição de Propostas", "Sair")) {
            case 1 -> fsm.atribuicaoAutoDocentes();
            case 2 -> fsm.orientadores();
            case 3 -> {
                switch (PAInput.chooseOption("Escolha uma opção: ", "Alunos com Orientador", "Alunos sem Orientador", "Dados sobre Orientadores", "Voltar atrás")) {
                    case 1 -> System.out.println(fsm.listarAlunosComOrientador());
                    case 2 -> System.out.println(fsm.listarAlunosSemOrientador());
                    case 3 -> System.out.println(fsm.dadosOrientadores());
                }
            }
            case 4 -> {
                String nome = PAInput.readString("Introduza o nome do ficheiro: ", false);
                if(fsm.exportarDadosFase4e5(nome))
                    System.out.println("Dados exportados com sucesso!\n");
                else
                    System.out.println("Erro na exportação dos dados\n");
            }
            case 5 -> {
                if(fsm.fechaFase())
                    System.out.println("Fase fechada com sucesso!");
                else
                    System.out.println("Impossibilidade de fechar a fase!");
            }
            case 6 -> fsm.fase3();
            default -> finish = true;
        }
    }

    private void gestaoOrientadoresUI() {
        System.out.println("\n*** Gestão de Orientadores ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Atribuir Orientadores a Alunos", "Consultar Orientadores e Respetivas Alunos", "Voltar atrás")) {
            case 1 -> fsm.esperaEscolha();
            case 2 -> System.out.println(fsm.listaOrientadoresEAlunos());
            case 3 -> fsm.fase4();
        }
    }

    private void esperaEscolhaUI() {
        System.out.println("\n*** Atribuição de Docente a Aluno ***");

        int orientador, aluno;

        System.out.println(fsm.listaAtribuicaoOrientadorAluno());

        orientador = PAInput.readInt("Introduza o número do orientador: ");

        aluno = PAInput.readInt("Introduza o número do aluno: ");

        if(fsm.atribuicaoOrientadorAluno(orientador, aluno))
            System.out.println("\nOrientador atribuído com sucesso!");
        else
            System.out.println("\nFalha na atribuição!");
    }

    private void fase5UI() {
        System.out.println("*** Consulta ***");
        switch (PAInput.chooseOption("Escolha uma opção:", "Alunos com Propostas Atribuídas", "Alunos sem Propostas Atribuídas", "Propostas Disponíveis", "Propostas Atribuídas", "Dados sobre as Orientações", "Exportar dados para ficheiro CSV", "Sair")) {
            case 1 -> System.out.println(fsm.listarAlunosComPropAtribuida());
            case 2 -> System.out.println(fsm.listaAlunosSemPropAtribuidaComCandidatura());
            case 3 -> System.out.println(fsm.listarPropNaoAtribuidas());
            case 4 -> System.out.println(fsm.listarPropAtribuidas());
            case 5 -> System.out.println(fsm.dadosOrientadores());
            case 6 -> {
                String nome = PAInput.readString("Introduza o nome do ficheiro: ", false);
                if(fsm.exportarDadosFase4e5(nome))
                    System.out.println("Dados exportados com sucesso!\n");
                else
                    System.out.println("Erro na exportação dos dados\n");
            }
            default -> finish = true;
        }
    }
}
