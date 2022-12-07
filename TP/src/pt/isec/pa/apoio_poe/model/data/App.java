package pt.isec.pa.apoio_poe.model.data;

import java.io.*;
import java.util.*;

/**
 * Classe responsável por gerir todos os dados da aplicação.
 * Tem um arraylist onde são guardados todos os alunos, um arraylist onde são
 * guardados todos os docentes, um arraylist onde são guardados todas as
 * propostas e outro onde são guardados os alunos que estão empatados na
 * atribução das propostas.
 * Tem dois hashmaps responsáveis por guardar o aluno e a sua proposta
 * e outro responsável por guardar o aluno, a sua proposta e o seu
 * orientador.
 * Tem ainda 4 variáveis booleanas para indicar se a fase1, fase 2,
 * fase3 e a fase4 estão fechadas.
 *
 * @author João Baptista
 * @author Pedro Sequeira
 */

public class App implements Serializable{
    private static final long serialVersionUID = 1L;

    private ArrayList<Aluno> alunos;
    private ArrayList<Docente> docentes;
    private ArrayList<Proposta> propostas;
    private ArrayList<Candidatura> candidaturas;
    private HashMap<Aluno, Proposta> atribuicaoPropostas;
    private HashMap<Map.Entry<Aluno, Proposta>, Docente> propostasFinais;
    private boolean fase1fechada;
    private boolean fase2fechada;
    private boolean fase3fechada;
    private boolean fase4fechada;
    private ArrayList<Aluno> empate;

    public boolean isFase1fechada() {
        return fase1fechada;
    }

    public boolean isFase2fechada() {
        return fase2fechada;
    }

    public boolean isFase3fechada() {
        return fase3fechada;
    }

    private static boolean verificaAluno(ArrayList<Aluno> alunos, long n_aluno){
        boolean flag = false, adiciona = true;
        for(Aluno al : alunos) {
            if (n_aluno == al.getNr_estudante()) {
                flag = true;
                break;
            }
        }
        if(!flag)
            adiciona = false;

        return adiciona;
    }

    private static boolean verificaCodigoProp(ArrayList<Proposta> propostas, String cod){
        boolean adiciona = true;
        for(Proposta prop : propostas){
            if(prop.getId().equals(cod)) {
                adiciona = false;
                break;
            }
        }
        return adiciona;
    }

    private static boolean verificaDocente(ArrayList<Docente> docentes, String email){
        boolean adiciona = false;
        for(Docente doc : docentes) {
            if (doc.getEmail().equals(email)) {
                adiciona = true;
                break;
            }
        }
        return adiciona;
    }

    private static boolean verificaRepeticaoAlunoPropostas(ArrayList<Proposta> propostas, long nAluno){
        boolean adiciona = true;
        for(Proposta prop : propostas){
            if(prop.getnAluno() == nAluno){
                adiciona = false;
                break;
            }
        }
        return adiciona;
    }

    private static boolean verificaRepeticaoAlunoCandidaturas(ArrayList<Candidatura> candidaturas, long nAluno){
        boolean adiciona = true;
        for(Candidatura cand : candidaturas){
            if(cand.getN_aluno() == nAluno){
                adiciona = false;
                break;
            }
        }
        return adiciona;
    }


    public App() {
        alunos = new ArrayList<>();
        docentes = new ArrayList<>();
        propostas = new ArrayList<>();
        candidaturas = new ArrayList<>();
        atribuicaoPropostas = new HashMap<>();
        propostasFinais = new HashMap<>();
        fase1fechada = false;
        fase2fechada = false;
        fase3fechada = false;
        fase4fechada = false;
        empate = new ArrayList<>();
    }

    public boolean lerAlunos(String nomeFich) throws NumberFormatException, InputMismatchException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeFich));
            Scanner sc = new Scanner(br);

            sc.useDelimiter(",|\n");

            while (sc.hasNext()){
                String n_alunoS = sc.next().trim();
                String nome = sc.next().trim();
                String email = sc.next().trim();
                String curso = sc.next().trim();
                String ramo = sc.next().trim();
                String classificacaoS = sc.next().trim();
                String EePS = sc.next().trim();

                boolean adiciona = true;

                //confirmação Classificação
                double classificacao = Double.parseDouble(classificacaoS);
                if(classificacao > 1.0 || classificacao < 0.0)
                    adiciona = false;

                //confirmação Ramo
                if(!ramo.equals("SI") && !ramo.equals("RAS") && !ramo.equals("DA"))
                    adiciona = false;

                //confirmação Curso
                if(!curso.equals("LEI") && !curso.equals("LEI-PL"))
                    adiciona = false;


                //confirmação EeP
                if(!EePS.equalsIgnoreCase("false") && !EePS.equalsIgnoreCase("true"))
                    adiciona = false;

                boolean EeP = Boolean.parseBoolean(EePS.trim());

                long n_aluno = Long.parseLong(n_alunoS.trim());

                for(Aluno al : alunos) {
                    if (n_aluno == al.getNr_estudante() || al.getEmail().equals(email)) {
                        adiciona = false;
                        break;
                    }
                }

                if(adiciona){
                    alunos.add(new Aluno(n_aluno, nome, email, curso, ramo, classificacao, EeP));
                }
            }

            br.close();
        } catch (IOException e){
            System.out.println("Não foi possível localizar o ficheiro!");
            return false;
        }catch (NumberFormatException | InputMismatchException r){
            r.printStackTrace();
        }

        return true;
    }

    public boolean eliminarAluno(long nr){
        if(alunos.size() == 0)
            return false;

        for(Aluno al : alunos){
            if(al.getNr_estudante() == nr) {
                alunos.remove(al);
                return true;
            }
        }
        return false;
    }

    public boolean lerDocentes(String nomeFich){
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeFich));
            Scanner sc = new Scanner(br);

            sc.useDelimiter(",|\\n");

            String email, nome;
            boolean adiciona;

            while (sc.hasNext()){
                adiciona = true;

                nome = sc.next();
                email = sc.next().trim();

                for(Docente doc : docentes){
                    if(doc.getEmail().equals(email)){
                        adiciona = false;
                        break;
                    }
                }
                if(adiciona){
                    docentes.add(new Docente(nome, email));
                }
            }

            br.close();
        }catch (IOException e){
            System.out.println("Não foi possível localizar o ficheiro!");
            return false;
        }
        return true;
    }

    public boolean eliminarDocente(String email){
        if(docentes.size() == 0)
            return false;

        for(Docente doc : docentes){
            if(doc.getEmail().equalsIgnoreCase(email)) {
                docentes.remove(doc);
                return true;
            }
        }
        return false;
    }


    public boolean lerPropostas(String nomeFich){
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeFich));
            Scanner sc2 = new Scanner(br);

            sc2.useDelimiter(",|\\n");

            String cod, titulo, ac, ramo, tipo, emailDocente, aluno, line;
            long n_aluno;
            boolean adiciona;

            while (sc2.hasNext()){
                Proposta novaProp = null;

                tipo = sc2.next();
                line = sc2.nextLine();

                Scanner sc = new Scanner(line);
                sc.useDelimiter(",|\n");

                if(tipo.equalsIgnoreCase("T1")){
                    cod = sc.next();
                    ramo = sc.next();
                    titulo = sc.next();
                    ac = sc.next();

                    adiciona = verificaCodigoProp(propostas, cod);

                    //confirmação Ramo
                    if(!ramo.equals("SI") && !ramo.equals("RAS") && !ramo.equals("DA") && !ramo.equals("RAS|SI|DA") && !ramo.equals("RAS|SI") && !ramo.equals("SI|RAS") && !ramo.equals("RAS|DA") && !ramo.equals("DA|RAS") && !ramo.equals("DA|SI") && !ramo.equals("SI|DA")) {
                        adiciona = false;
                    }

                    if(sc.hasNext()) {
                        aluno = sc.next();
                        n_aluno = Long.parseLong(aluno);

                        if(adiciona)
                            adiciona = verificaAluno(alunos, n_aluno);

                        if(adiciona)
                            adiciona = verificaRepeticaoAlunoPropostas(propostas, n_aluno);

                        if(adiciona)
                            novaProp = new Estagio(cod, ramo, titulo, ac, n_aluno);
                    }else
                        if(adiciona)
                            novaProp = new Estagio(cod, ramo, titulo, ac);
                }else if(tipo.equalsIgnoreCase("T2")) {
                    cod = sc.next();
                    ramo = sc.next();
                    titulo = sc.next();
                    emailDocente = sc.next().trim();

                    adiciona = verificaDocente(docentes, emailDocente);

                    //confirmação Ramo
                    if(!ramo.equals("SI") && !ramo.equals("RAS") && !ramo.equals("DA") && !ramo.equals("RAS|SI|DA") && !ramo.equals("RAS|SI") && !ramo.equals("SI|RAS") && !ramo.equals("RAS|DA") && !ramo.equals("DA|RAS") && !ramo.equals("DA|SI") && !ramo.equals("SI|DA")) {
                        adiciona = false;
                    }

                    if(adiciona)
                        adiciona = verificaCodigoProp(propostas, cod);

                    if (sc.hasNext()) {
                        aluno = sc.next();
                        n_aluno = Long.parseLong(aluno);

                        if(adiciona)
                            adiciona = verificaAluno(alunos, n_aluno);

                        if(adiciona)
                            adiciona = verificaRepeticaoAlunoPropostas(propostas, n_aluno);

                        if(adiciona)
                            novaProp = new Projeto(cod, ramo, titulo, emailDocente, n_aluno);
                    }else
                        if(adiciona)
                            novaProp = new Projeto(cod, ramo, titulo, emailDocente);
                }else{
                    cod = sc.next();
                    titulo = sc.next();
                    aluno = sc.next();
                    n_aluno = Long.parseLong(aluno);

                    adiciona = verificaAluno(alunos, n_aluno);

                    if(adiciona)
                        adiciona = verificaRepeticaoAlunoPropostas(propostas, n_aluno);

                    if(adiciona)
                        adiciona = verificaCodigoProp(propostas, cod);

                    if(adiciona)
                        novaProp = new Autoproposta(cod, titulo, n_aluno);
                }

                if(adiciona)
                    propostas.add(novaProp);
            }

            br.close();
        }catch (IOException e){
            System.out.println("Não foi possível localizar o ficheiro!");
            return false;
        }catch (NumberFormatException e){
            System.out.println("Formato do ficheiro incorreto!");
        }
        return true;
    }

    public boolean eliminarProposta(String id){
        if(docentes.size() == 0)
            return false;

        for(Proposta prop : propostas){
            if(prop.getId().equalsIgnoreCase(id)) {
                propostas.remove(prop);
                return true;
            }
        }
        return false;
    }

    public boolean lerCandidaturas(String nomeFich){
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeFich));
            Scanner sc = new Scanner(br);

            sc.useDelimiter(",|\\n");

            ArrayList<String> codigos = new ArrayList<>();
            String codigo;
            long aluno;
            String line;
            boolean adiciona;

            while (sc.hasNext()) {
                Candidatura novaCand;
                codigos.clear();

                String alunoS = sc.next().trim();

                aluno = Long.parseLong(alunoS);

                line = sc.nextLine();

                adiciona = verificaAluno(alunos, aluno);

                if(adiciona)
                    adiciona = verificaRepeticaoAlunoPropostas(propostas, aluno);

                if(adiciona)
                    adiciona = verificaRepeticaoAlunoCandidaturas(candidaturas, aluno);

                Scanner sc2 = new Scanner(line);
                while (sc2.hasNext()) {
                    sc2.useDelimiter(",");
                    codigo = sc2.next();

                    for(Proposta prop : propostas)
                        if(prop.getnAluno() == -1 && prop.getId().equals(codigo))
                            codigos.add(codigo);
                }

                if(codigos.size() == 0)
                    adiciona = false;

                if(adiciona) {
                    novaCand = new Candidatura(aluno, codigos);
                    candidaturas.add(novaCand);
                }
            }

            br.close();
        }catch (IOException e){
            System.out.println("Não foi possível localizar o ficheiro!");
            return false;
        }
        return true;
    }

    public boolean eliminarCandidatura(long nr){
        if(candidaturas.size() == 0)
            return false;

        for(Candidatura cand : candidaturas){
            if(cand.getN_aluno() == nr) {
                candidaturas.remove(cand);
                return true;
            }
        }
        return false;
    }

    public boolean escreverAlunos(String nome) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(nome);

            for (Aluno al : alunos) {
                String classificacao = Double.toString(al.getClassificacao());
                pw.printf("%d, %s, %s, %s, %s, %s, %s\n", al.getNr_estudante(), al.getNome(), al.getEmail(), al.getCurso(), al.getRamo(), classificacao, al.isEeP());
            }

            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }
    }

    public boolean escreverDocentes(String nome) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(nome);

            for (Docente doc : docentes) {
                pw.printf("%s, %s\n", doc.getNome(), doc.getEmail());
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }
    }

    public boolean escreverPropostas(String nome) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(nome);

            for (Proposta prop : propostas) {
                if (prop instanceof Estagio)
                    pw.printf("T1,%s,%s,%s,%s\n", prop.getId(), prop.getRamo(), prop.getTitulo(), ((Estagio) prop).getId_acolhimento());
                else if (prop instanceof Projeto)
                    pw.printf("T2,%s,%s,%s,%s,%d\n", prop.getId(), prop.getRamo(), prop.getTitulo(), ((Projeto) prop).getEmailDocente(), prop.getnAluno());
                else
                    pw.printf("T3,%s,%s,%d\n", prop.getId(), prop.getTitulo(), prop.getnAluno());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }
    }

    public boolean escreverCandidaturas(String nome) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(nome);

            for (Candidatura cand : candidaturas) {
                pw.printf("%d,", cand.getN_aluno());
                for (String prop : cand.getCod_prop())
                    pw.printf("%s,", prop);
                pw.printf("\n");
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(pw != null)
                pw.close();
        }
    }

    public boolean fechaFase1(){
        int da = 10, ras = 10, si = 10;

        System.out.println(alunos.size());
        System.out.println(docentes.size());
        System.out.println(propostas.size());

        for(Proposta prop : propostas){
            String ramos;
            ramos = prop.getRamo();
            if(ramos == null)
                continue;
            Scanner sc = new Scanner(ramos);
            sc.useDelimiter("\\|");
            while (sc.hasNext()){
                String valor = sc.next();
                if(valor.equals("DA"))
                    da++;
                else if(valor.equals("RAS"))
                    ras++;
                else
                    si++;
            }
        }

        if(ras >= alunos.size() && da >= alunos.size() && si >= alunos.size())
            fase1fechada = true;

        return fase1fechada;
    }

    public String listaAlunos(){
        if(alunos.size() == 0){
            return "Sem alunos!";
        }

        StringBuilder sb = new StringBuilder();
        for(Aluno al : alunos)
            sb.append(al + "\n");

        return sb.toString();
    }

    public String listaDocentes(){
        if(docentes.size() == 0) {
            return "Sem docentes!";
        }

        StringBuilder sb = new StringBuilder();
        for(Docente doc : docentes)
            sb.append(doc + "\n");

        return sb.toString();
    }

    public String listaPropostas(){
        if(propostas.size() == 0)
            return "Sem propostas!";

        StringBuilder sb = new StringBuilder();
        for(Proposta prop : propostas)
            sb.append(prop + "\n");

        return sb.toString();
    }

    public String listaCandidaturas(){
        if(candidaturas.size() == 0)
            return "Sem candidaturas!";

        StringBuilder sb = new StringBuilder();
        for(Candidatura cand : candidaturas)
            sb.append(cand + "\n");

        return sb.toString();
    }

    public String listaPropSemCand(){
        if(candidaturas.size() == 0)
            return "Sem propostas!";

        StringBuilder sb = new StringBuilder();
        for(Proposta prop : propostas){
            boolean flag = false;
            for(Candidatura cand : candidaturas) {
                ArrayList<String> codigos = cand.getCod_prop();
                for (String codigo : codigos) {
                    if (prop.getId().equals(codigo)) {
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag){
                sb.append(prop + "\n");
            }
        }


        return sb.toString();
    }

    public String listaPropComCand(){
        if(candidaturas.size() == 0 || propostas.size() == 0)
            return "Sem candidaturas ou sem propostas!";

        StringBuilder sb = new StringBuilder();
        propCiclo:
        for (Proposta prop : propostas) {
            for(Candidatura cand : candidaturas) {
                ArrayList<String> codigos = cand.getCod_prop();
                    for (String codigo : codigos) {
                        if (prop.getId().equals(codigo)) {
                            sb.append(prop + "\n");
                            continue propCiclo;
                        }
                    }
                }
        }

        return sb.toString();
    }

    public String listaAlunosComCand(){
        if(candidaturas.size() == 0 || alunos.size() == 0)
            return "Sem candidaturas ou sem alunos!";

        StringBuilder sb = new StringBuilder();

        for(Candidatura cand : candidaturas){
            long n_aluno = cand.getN_aluno();
            for(Aluno al : alunos){
                if(al.getNr_estudante() == n_aluno)
                    sb.append(al + "\n");
            }
        }
        return sb.toString();
    }

    public String listaAlunosSemCand(){
        if(candidaturas.size() == 0 || alunos.size() == 0)
            return "Sem candidaturas ou sem alunos!";

        StringBuilder sb = new StringBuilder();
        boolean flag;

        for(Aluno al : alunos){
            flag = false;
            for(Candidatura cand : candidaturas){
                long n_aluno = cand.getN_aluno();
                if(al.getNr_estudante() == n_aluno){
                    flag = true;
                }
            }
            if(!flag)
                sb.append(al + "\n");
        }
        return sb.toString();
    }

    public String listaAlunosAutopropostas(){
        if(propostas.size() == 0 || alunos.size() == 0)
            return "Sem candidaturas ou sem alunos!";

        StringBuilder sb = new StringBuilder();

        for(Proposta prop : propostas){
            if(prop instanceof Autoproposta){
                long n_aluno = prop.getnAluno();
                for(Aluno al : alunos){
                    if(al.getNr_estudante() == n_aluno)
                        sb.append(al + "\n");
                }
            }
        }
        return sb.toString();
    }

    public String listaAutopropostas(){
        if(propostas.size() == 0 || alunos.size() == 0)
            return "Sem candidaturas ou sem alunos!";

        StringBuilder sb = new StringBuilder();

        for(Proposta prop : propostas){
            if(prop instanceof Autoproposta){
                sb.append(prop + "\n");
                }
            }
        return sb.toString();
    }

    public String listaPropDocentes(){
        if(propostas.size() == 0 || docentes.size() == 0)
            return "Sem candidaturas ou sem docentes!";

        StringBuilder sb = new StringBuilder();

        for(Proposta prop : propostas){
            if(prop instanceof Projeto){
                sb.append(prop + "\n");
                }
            }
        return sb.toString();
    }

    public boolean fechaFase2(){
        if(fase1fechada)
            fase2fechada = true;

        return fase2fechada;
    }

    public boolean atribuicaoAutoPropostasEDocentes() {
        if(propostas.size() == 0 || alunos.size() == 0 || candidaturas.size() == 0)
            return false;

        for(Proposta prop : propostas){
            if(prop.getnAluno() != -1){
                for(Aluno al : alunos){
                    if(al.getNr_estudante() == prop.getnAluno())
                        atribuicaoPropostas.put(al, prop);
                }
            }
        }

        return true;
    }

    public boolean atribuicaoPropostas(){
        try {
            ArrayList<Aluno> notas = new ArrayList<>();
            boolean adiciona;

            for (Aluno al : alunos) {
                adiciona = true;
                for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                    if (al.getNr_estudante() == set.getKey().getNr_estudante()) {
                        adiciona = false;
                        break;
                    }
                }
                if (adiciona)
                    notas.add(al);
            }

            class ClassificacaoComparator implements Comparator<Aluno> {
                @Override
                public int compare(Aluno o1, Aluno o2) {
                    return -Double.compare(o1.getClassificacao(), o2.getClassificacao());       // sinal negativo para ficar por ordem decrescente
                }
            }

            ClassificacaoComparator comparator = new ClassificacaoComparator();

            Collections.sort(notas, comparator);

            int i;
            int j = 0;
            boolean flag = true;
            for (Aluno al : notas) {
                if (flag) {
                    if (al.getClassificacao() == notas.get(j + 1).getClassificacao()) {
                        empate.add(notas.get(j));
                        empate.add(notas.get(j + 1));
                        notas.remove(notas.get(j));
                        notas.remove(notas.get(j + 1));
                        return false;
                    }
                }
                candidatura:
                for (Candidatura cand : candidaturas) {
                    if (al.getNr_estudante() == cand.getN_aluno()) {
                        for (i = 0; i < cand.getCod_prop().size(); i++) {
                            proposta:
                            for (Proposta prop : propostas) {
                                if (prop.getId().equals(cand.getCod_prop().get(i)))
                                    if (prop instanceof Estagio && al.isEeP()) {
                                        for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                                            if (prop.getId().equals(set.getValue().getId())) {
                                                break proposta;
                                            }
                                        }
                                        atribuicaoPropostas.put(al, prop);
                                        break candidatura;
                                    } else if (prop instanceof Projeto) {
                                        for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                                            if (prop.getId().equals(set.getValue().getId())) {
                                                break proposta;
                                            }
                                        }
                                        atribuicaoPropostas.put(al, prop);
                                        break candidatura;
                                    } else
                                        break;
                            }
                        }
                    }
                }
                if (j < notas.size() - 1)
                    j++;
                else
                    flag = false;
            }

            return true;
        }catch (IndexOutOfBoundsException e){
            return true;
        }
    }

    public String listaAlunosEmpate(){
        StringBuilder sb = new StringBuilder();
        int j = 0, k;

        for(Aluno al : empate) {
            k = 0;
            sb.append(++j + " " + " - " + al + "\n");
            for(Candidatura cand : candidaturas){
                if(cand.getN_aluno() == al.getNr_estudante()){
                    for(int i = 0; i < cand.getCod_prop().size(); i++) {
                        for (Proposta prop : propostas) {
                            if (prop.getId().equals(cand.getCod_prop().get(i))) {
                                sb.append("\t" + " " + ++k + " - " +  prop.getId() + " - " + prop.getTitulo() + "\n");
                            }
                        }
                    }
                }
            }
            sb.append("\n");

        }
        return sb.toString();
    }

    public boolean AtribuicaodecideEmpate(int aluno, int prop){

        if(aluno > empate.size() || aluno < 0)
            return false;

        candidatura: for(Candidatura cand : candidaturas){
            if(prop > cand.getCod_prop().size() || prop < 0)
                return false;
                if(empate.get(aluno-1).getNr_estudante() == cand.getN_aluno()){
                    for(Proposta proposta : propostas) {
                        if(proposta.getId().equals(cand.getCod_prop().get(prop-1))){
                            atribuicaoPropostas.put(empate.get(aluno-1), proposta);
                            empate.clear();
                            break candidatura;
                    }
                }
            }
        }
        return true;
    }

    public String listaAtribuicaoManualPropostas(){
        if (atribuicaoPropostas.size() == alunos.size() || atribuicaoPropostas.size() == propostas.size()){
            return "";
        }

        int i = 0, j;
        boolean escreve;

        StringBuilder sb = new StringBuilder();

        sb.append("Alunos: " + "\n");
        for(Aluno al : alunos) {
            escreve = true;
            for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                if (set.getKey().getNr_estudante() == al.getNr_estudante()) {
                    escreve = false;
                    break;
                }
            }
            if (escreve) {
                sb.append("\t" + ++i + " - " + al.getNome() + "\n");
            }
        }

        j = 0;
        sb.append("\nPropostas: " + "\n");
        for(Proposta prop : propostas){
            escreve = true;
            for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                if(prop.getId().equals(set.getValue().getId())) {
                    escreve = false;
                }
            }
            if(escreve) {
                sb.append("\t" + ++j + " - " + prop.getId() + "\n");
            }
        }

        return sb.toString();
    }

    public boolean atribuicaoManualPropostas(int propIndex, int aluno){
        try {
            if (!verificaTamanhos()){
                return false;
            }

            propIndex--;        //Porque os índices começam em 0
            aluno--;

            boolean adiciona;
            int i = 0, j = 0;
            String[] nomeAlunos = new String[alunos.size() + 1];
            String[] IdProp = new String[propostas.size() + 1];

            for (Aluno al : alunos) {
                adiciona = true;
                for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                    if (set.getKey().getNr_estudante() == al.getNr_estudante()) {
                        adiciona = false;
                        break;
                    }
                }
                if (adiciona) {
                    nomeAlunos[i] = al.getNome();
                    i++;
                }
            }

            for (Proposta prop : propostas) {
                adiciona = true;
                for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                    if (prop.getId().equals(set.getValue().getId())) {
                        adiciona = false;
                        break;
                    }
                }
                if (adiciona) {
                    IdProp[j] = prop.getId();
                    j++;
                }
            }


            for (Aluno al : alunos) {
                if (nomeAlunos[aluno].equals(al.getNome())) {
                    for (Proposta prop : propostas) {
                        if (prop.getId().equals(IdProp[propIndex])) {
                            if (prop instanceof Estagio && !al.isEeP()) {
                                return false;
                            }else {
                                atribuicaoPropostas.put(al, prop);
                                return true;
                            }
                        }
                    }
                }
            }
            return false;

        }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }

    public boolean verificaTamanhos(){
        return atribuicaoPropostas.size() != alunos.size() && atribuicaoPropostas.size() != propostas.size();
    }

    public String listaRemocaoManualPropostas(){

        if(atribuicaoPropostas.size() == 0)
            return "";

        int i = 0;
        StringBuilder sb = new StringBuilder();

        sb.append("Alunos e respetivas propostas: \n");
        for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
            sb.append(++i + " - " + set.getKey().getNr_estudante() + " (" + set.getValue().getId() + ")" + "\n");
        }

        return sb.toString();
    }

    public boolean remocaoManualPropostas(int i){
        try {
            if (atribuicaoPropostas.size() == 0)
                return false;

            int j;

            j = 0;
            Aluno temp = null;
            for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                if (j == i - 1) {
                    if(set.getValue() instanceof Autoproposta || set.getValue().getnAluno() != -1){
                        return false;
                    }else
                        temp = set.getKey();
                }
                j++;
            }

            atribuicaoPropostas.remove(temp);

            return true;

        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    public boolean remocaoTodasPropostas(){

        boolean remove;

        if (atribuicaoPropostas.size() == 0)
            return false;

        Iterator<Map.Entry<Aluno, Proposta>> it = atribuicaoPropostas.entrySet().iterator();
        while (it.hasNext()) {
            remove = true;
            Map.Entry<Aluno, Proposta> item = it.next();
            if(item.getValue() instanceof Autoproposta || item.getValue().getnAluno() != -1){
                remove = false;
            }

            if(remove)
                it.remove();
        }

        return true;
    }

    public String listaAlunosComPropAtribuida(){
        if(propostas.size() == 0 || alunos.size() == 0)
            return "Sem candidaturas ou sem alunos!";

        StringBuilder sb = new StringBuilder();
        boolean escreve;

        for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
            long n_aluno = set.getKey().getNr_estudante();
            for(Aluno al : alunos){
                if(al.getNr_estudante() == n_aluno) {
                    if (set.getValue() instanceof Autoproposta)
                        sb.append("(Preferência: 1) - " + al + "\n");
                    else {
                        escreve = true;
                        for(Candidatura cand : candidaturas){
                            if(cand.getN_aluno() == al.getNr_estudante()){
                                for(int i = 0; i < cand.getCod_prop().size(); i++){
                                    if(set.getValue().getId().equals(cand.getCod_prop().get(i))) {
                                        sb.append("(Preferência: " + (i+1) + ") - " + al + "\n");
                                        escreve = false;
                                    }
                                }
                            }
                        }
                        if(escreve)
                            sb.append("(Preferência: -) - " + al + "\n");
                    }
                }

            }
        }
        return sb.toString();
    }

    public String listaAlunosSemPropAtribuida(){
        if(propostas.size() == 0 || alunos.size() == 0)
            return "Sem candidaturas ou sem alunos!";

        StringBuilder sb = new StringBuilder();
        boolean adiciona;

        for(Aluno al : alunos){
            adiciona = true;
            for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                long n_aluno = set.getKey().getNr_estudante();
                if (al.getNr_estudante() == n_aluno) {
                    adiciona = false;
                    break;
                }
            }
            if(adiciona)
                sb.append(al + "\n");
        }
        return sb.toString();
    }

    public String listaPropAtribuidas(){
        if(propostas.size() == 0)
            return "Sem candidaturas!";

        StringBuilder sb = new StringBuilder();

        for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet())
            sb.append(set.getValue() + "\n");

        return sb.toString();
    }

    public String listaPropNaoAtribuidas(){
        if(propostas.size() == 0)
            return "Sem candidaturas!";

        StringBuilder sb = new StringBuilder();
        boolean adiciona;

        for(Proposta prop : propostas) {
            adiciona = true;
            for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                if (prop.getId().equals(set.getValue().getId())) {
                    adiciona = false;
                    break;
                }
            }
            if(adiciona)
                sb.append(prop + "\n");
        }

        return sb.toString();
    }

    public boolean exportarDadosFase3(String nome){
        PrintWriter pw = null;
        String preferida = null;
        int ordem = 0;

        try {
            pw = new PrintWriter(nome);

            for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                for(Candidatura cand : candidaturas){
                    if(set.getKey().getNr_estudante() == cand.getN_aluno()){
                        pw.printf("%s,", set.getKey().getNome());
                        for(int i = 0; i < cand.getCod_prop().size(); i++) {
                            pw.printf("%s,", cand.getCod_prop().get(i));
                            if (cand.getCod_prop().get(i).equals(set.getValue().getId())) {
                                preferida = set.getValue().getId();
                                ordem = i + 1;
                            }
                        }
                        pw.printf("%s,%d\n",preferida, ordem);
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }
    }

    public boolean exportarDadosFase4e5(String nome){
        PrintWriter pw = null;
        String preferida = null;
        String orientador = "-";
        int ordem = 0;

        try {
            pw = new PrintWriter(nome);

            for (Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set : propostasFinais.entrySet()) {
                for(Candidatura cand : candidaturas){
                    if(set.getKey().getKey().getNr_estudante() == cand.getN_aluno()){
                        pw.printf("%s,", set.getKey().getKey().getNome());
                        for(int i = 0; i < cand.getCod_prop().size(); i++) {
                            pw.printf("%s,", cand.getCod_prop().get(i));
                            if (cand.getCod_prop().get(i).equals(set.getKey().getValue().getId())) {
                                preferida = set.getKey().getValue().getId();
                                ordem = i + 1;
                                for (Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set2 : propostasFinais.entrySet()) {
                                    if(set2.getKey().getKey().getNr_estudante() == set.getKey().getKey().getNr_estudante())
                                        orientador = set2.getValue().getNome();
                                }
                            }
                        }
                        pw.printf("%s,%d,%s\n",preferida, ordem, orientador);
                    }
                }
            }
            boolean adiciona;
            for (Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set : propostasFinais.entrySet()) {
                adiciona = true;
                for (Candidatura cand : candidaturas) {
                    if (set.getKey().getKey().getNr_estudante() == cand.getN_aluno()) {
                        adiciona = false;
                        break;
                    }
                }
                if(adiciona)
                    pw.printf("%s, %s, %s, 1, %s\n", set.getKey().getKey().getNome(), set.getKey().getValue().getId(), set.getKey().getValue().getId(), set.getValue().getNome());
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }
    }

    public boolean fechaFase3(){
        if(!fase2fechada)
            return false;

        int nAlunos = 0;
        for(Candidatura cand : candidaturas){
            for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                if(cand.getN_aluno() == set.getKey().getNr_estudante())
                    nAlunos++;
            }
        }
        if(nAlunos == candidaturas.size())
            fase3fechada = true;

        return fase3fechada;
    }

    public boolean atribuicaoAutoDocentes(){
        if(atribuicaoPropostas.size() == 0 || docentes.size() == 0)
            return false;

        for(Proposta proj : propostas){
            if(proj instanceof Projeto){
                for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                    if(set.getValue().getId().equals(proj.getId())){
                        for(Docente doc : docentes)
                            if(doc.getEmail().equals(((Projeto) proj).getEmailDocente()))
                                propostasFinais.put(set, doc);
                    }
                }
            }
        }

        return true;
    }

    public String listaAtribuicaoOrientadorAluno(){
        if(atribuicaoPropostas.size() == propostasFinais.size())
            return "";

        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean escreve;

        sb.append("\nOrientadores: \n");
        for(Docente doc : docentes) {
            sb.append("\t" + ++i + " - " + doc.getNome() + "\n");
        }

        i = 0;
        sb.append("Alunos: \n");
        for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()){
            escreve = true;
            for(Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set2 : propostasFinais.entrySet()){
                if(set.getKey().getNr_estudante() == set2.getKey().getKey().getNr_estudante()) {
                    escreve = false;
                    break;
                }
            }
            if(escreve) {
                sb.append("\t" + ++i + " - " + set.getKey().getNome() + "(" + set.getValue().getId() + ")" + "\n");
            }
        }

        return sb.toString();
    }

    public boolean atribuicaoOrientadorAluno(int orientador, int aluno){

        try {

            if (atribuicaoPropostas.size() == propostasFinais.size())
                return false;

            int i = 0;
            boolean escreve;
            String[] nomeAlunos = new String[atribuicaoPropostas.size() + 1];
            String[] nomeOrientadores = new String[docentes.size() + 1];
            Aluno alunoTemp = null;

            for (Docente doc : docentes) {
                nomeOrientadores[i] = doc.getNome();
                i++;
            }

            i = 0;
            for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                escreve = true;
                for (Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set2 : propostasFinais.entrySet()) {
                    if (set.getKey().getNr_estudante() == set2.getKey().getKey().getNr_estudante()) {
                        escreve = false;
                        break;
                    }
                }
                if (escreve) {
                    nomeAlunos[i] = set.getKey().getNome();
                    i++;
                }
            }

            for (Aluno al : alunos) {
                if (nomeAlunos[aluno-1].equals(al.getNome())) {
                    alunoTemp = al;
                }
            }

            for (Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                if (alunoTemp.getNr_estudante() == set.getKey().getNr_estudante()) {
                    for (Docente doc : docentes) {
                        if (nomeOrientadores[orientador-1].equals(doc.getNome())) {
                            propostasFinais.put(set, doc);
                            return true;
                        }
                    }
                }
            }

            return false;
        }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }

    public String listaOrientadoresEAlunos(){
        if(propostasFinais.size() == 0)
            return "Sem orientadores atribuídos";

        StringBuilder sb = new StringBuilder();

        sb.append("Orientadores e respetivos Alunos: \n");

        for(Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set : propostasFinais.entrySet()){
            sb.append(set.getValue().getNome() + " - " + set.getKey().getKey().getNome() + "\n");
        }

        return sb.toString();
    }

    public String listaAlunosComOrientador(){
        if(propostasFinais.size() == 0)
            return "Sem propostas!";

        StringBuilder sb = new StringBuilder();

        for(Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set : propostasFinais.entrySet()) {
            for(Aluno al : alunos){
                if(set.getKey().getKey().getNr_estudante() == al.getNr_estudante())
                    sb.append(al + "\n");
            }
        }

        return sb.toString();
    }

    public String listaAlunosSemOrientador(){
        if(propostasFinais.size() == 0)
            return "Sem propostas!";

        StringBuilder sb = new StringBuilder();
        boolean adiciona;

        for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
            adiciona = true;
            for (Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set2 : propostasFinais.entrySet()) {
                if (set.getKey().getNr_estudante() == set2.getKey().getKey().getNr_estudante()) {
                    adiciona = false;
                    break;
                }
            }
            for(Aluno al : alunos){
                if(al.getNr_estudante() == set.getKey().getNr_estudante() && adiciona)
                    sb.append(al + "\n");
            }
        }

        return sb.toString();
    }

    public String dadosOrientadores(){
        if(propostasFinais.size() == 0)
            return "Sem propostas!";

        StringBuilder sb = new StringBuilder();
        int i, k, l = 0;
        ArrayList<String> nomesTemp = new ArrayList<>();
        boolean adiciona;
        int soma = 0;

        //Número de orientações por docente
        sb.append("Número de orientações por docente:" + "\n");
        for(Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set : propostasFinais.entrySet()){
            i = 0;
            adiciona = true;
            for(String nomes : nomesTemp) {
                if (set.getValue().getNome().equals(nomes)) {
                    adiciona = false;
                    break;
                }
            }

            if(adiciona)
                nomesTemp.add(set.getValue().getNome());

            for(Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set2 : propostasFinais.entrySet()){
                if(set.getValue().getEmail().equals(set2.getValue().getEmail()))
                    i++;
            }

            if(adiciona) {
                sb.append("\t" + set.getValue().getNome() + " -> " + i + "\n");
                soma += i;
            }
        }

        //Média/Minimo/Máximo de orientações
        ArrayList<Integer> valores = new ArrayList<>();
        int minimo = 1000;
        int maximo = 0;
        String nomeMax = "", nomeMin = "";

        for(Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set : propostasFinais.entrySet()) {
            i = 0;
            for (Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set2 : propostasFinais.entrySet()) {
                if (set.getValue().getEmail().equals(set2.getValue().getEmail()))
                    i++;
            }
            valores.add(i);

            if(i > maximo){
                maximo = i;
                nomeMax = set.getValue().getNome();
            }
            if(i < minimo){
                minimo = i;
                nomeMin = set.getValue().getNome();
            }

        }

        float media = (float) soma / nomesTemp.size();
        sb.append("Média de orientações: " + media + "\n");
        sb.append("Máximo de orientações: " + nomeMax + "\n");
        sb.append("Mínimo de orientações: " + nomeMin + "\n");

        return sb.toString();
    }

    public String listaAlunosSemPropAtribuidaComCandidatura(){
        if(propostas.size() == 0 || alunos.size() == 0)
            return "Sem candidaturas ou sem alunos!";

        StringBuilder sb = new StringBuilder();
        boolean adiciona;
        long n_aluno = 0;

        for(Candidatura cand : candidaturas){
            adiciona = true;
            for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
                n_aluno = set.getKey().getNr_estudante();
                if (cand.getN_aluno() == n_aluno) {
                    adiciona = false;
                    break;
                }
            }
            if(adiciona)
                for(Aluno al : alunos)
                    if(n_aluno == al.getNr_estudante())
                        sb.append(al.getNr_estudante() + "\n");
        }
        return sb.toString();
    }

    public boolean fechaFase4(){
        if(fase3fechada)
            fase4fechada = true;

        return fase4fechada;
    }

    public boolean removeAtribuicaoProposta(Aluno aluno) {
        for(Map.Entry<Aluno, Proposta> set : atribuicaoPropostas.entrySet()) {
            if(aluno.getNr_estudante() == set.getKey().getNr_estudante()){
                atribuicaoPropostas.remove(set.getKey());
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Integer> getTopDocentes(){
        HashMap<String, Integer> top = new HashMap<>();

        int i;

        for(Docente doc : docentes){
            top.put(doc.getNome(), 0);
        }

        for(Map.Entry<String, Integer> set : top.entrySet()){
            i = 0;

            for(Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set2 : propostasFinais.entrySet()){
                if(set.getKey().equals(set2.getValue().getNome()))
                    top.put(set.getKey(), ++i);
            }
        }

        return top;
    }

    public HashMap<String, Integer> getEstagiosPorRamos(){
        HashMap<String, Integer> grafico = new HashMap<>();
        int i, da = 0, si = 0, ras = 0;
        boolean res;
        ArrayList<String> estagios = new ArrayList<>();
        String s;

        grafico.put("DA", 0);
        grafico.put("RAS", 0);
        grafico.put("SI", 0);

        for(Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set : propostasFinais.entrySet()) {
            String ramos;
            ramos = set.getKey().getValue().getRamo();
            if (ramos == null)
                continue;
            Scanner sc = new Scanner(ramos);
            sc.useDelimiter("\\|");
            while (sc.hasNext()) {
                String valor = sc.next();
                if (valor.equals("DA"))
                    grafico.put("DA", ++da);
                else if (valor.equals("RAS"))
                    grafico.put("RAS", ++ras);
                else
                    grafico.put("SI", ++si);
            }
        }

        /*for(Map.Entry<Map.Entry<Aluno, Proposta>, Docente> set : propostasFinais.entrySet()){
            if(set.getKey().getValue().getRamo() == null){
            }else if(set.getKey().getValue().getRamo().contains("|")) {
                Scanner sc = new Scanner(set.getKey().getValue().getRamo());
                estagios.clear();

                sc.useDelimiter("\\|");

                while (sc.hasNext()) {
                    s = sc.next();
                    estagios.add(s);
                }

                if(estagios.size() == 2){
                    for (Map.Entry<String, Integer> set2 : grafico.entrySet()) {
                        if (set.getKey().getValue().getRamo() != null) {
                            if (estagios.get(0).equals(set2.getKey())) {
                                i = set2.getValue();
                                grafico.put(set2.getKey(), ++i);
                            }
                        }
                    }
                    for (Map.Entry<String, Integer> set2 : grafico.entrySet()) {
                        if (set.getKey().getValue().getRamo() != null) {
                            if (estagios.get(1).equals(set2.getKey())) {
                                i = set2.getValue();
                                grafico.put(set2.getKey(), ++i);
                            }
                        }
                    }
                }else if(estagios.size() == 3){
                    for (Map.Entry<String, Integer> set2 : grafico.entrySet()) {
                        if (estagios.get(0).equals(set2.getKey())) {
                            i = set2.getValue();
                            grafico.put(set2.getKey(), ++i);
                        }
                    }
                    for (Map.Entry<String, Integer> set2 : grafico.entrySet()) {
                        if (estagios.get(1).equals(set2.getKey())) {
                            i = set2.getValue();
                            grafico.put(set2.getKey(), ++i);
                        }
                    }
                    for (Map.Entry<String, Integer> set2 : grafico.entrySet()) {
                        if (estagios.get(2).equals(set2.getKey())) {
                            i = set2.getValue();
                            grafico.put(set2.getKey(), ++i);
                        }
                    }
                }
            }else{
                for(Map.Entry<String, Integer> set2 : grafico.entrySet()) {
                    if (set.getKey().getValue().getRamo().equals(set2.getKey())) {
                        i = set2.getValue();
                        grafico.put(set2.getKey(), ++i);
                    }
                }
            }

        }*/

        return grafico;
    }

    public int getAlunos(){
        return alunos.size();
    }

    public int getDocentes() {
        return docentes.size();
    }

    public int getPropostas(){
        return propostas.size();
    }

    public int getCandidaturas(){
        return candidaturas.size();
    }

    public int getAlunosComPropAtribuida() {
        return atribuicaoPropostas.size();
    }

    public int getAlunosComOrientador(){
        return propostasFinais.size();
    }

    public boolean adicionarAluno(long n_aluno, String nome, String email, String curso, String ramo, double classificacao, boolean EeP){
        return alunos.add(new Aluno(n_aluno, nome, email, curso, ramo, classificacao, EeP));
    }

    public boolean adicionarDocente(String nome, String email){
        return docentes.add(new Docente(nome, email));
    }

    public boolean adicionarProjetoCAluno(String id, String ramo, String titulo, String email, long aluno){
        return propostas.add(new Projeto(id, ramo, titulo, email, aluno));
    }

    public boolean adicionarProjetoSAluno(String id, String ramo, String titulo, String email){
        return propostas.add(new Projeto(id, ramo, titulo, email));
    }

    public boolean adicionarEstagioCAluno(String id, String ramo, String titulo, String idAco, long aluno){
        return propostas.add(new Estagio(id, ramo, titulo, idAco, aluno));
    }

    public boolean adicionarEstagioSAluno(String id, String ramo, String titulo, String idAco){
        return propostas.add(new Estagio(id, ramo, titulo, idAco));
    }

    public boolean adicionarAutoprop(String id, String titulo, long nAluno){
        return propostas.add(new Autoproposta(id, titulo, nAluno));
    }
}
