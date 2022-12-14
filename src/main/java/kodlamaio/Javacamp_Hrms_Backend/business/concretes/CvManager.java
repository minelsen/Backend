package kodlamaio.Javacamp_Hrms_Backend.business.concretes;

import kodlamaio.Javacamp_Hrms_Backend.business.abstracts.CvService;
import kodlamaio.Javacamp_Hrms_Backend.core.utilities.results.*;
import kodlamaio.Javacamp_Hrms_Backend.dataAccess.abstracts.CandidateDao;
import kodlamaio.Javacamp_Hrms_Backend.dataAccess.abstracts.CvDao;
import kodlamaio.Javacamp_Hrms_Backend.entities.concretes.Cv;
import kodlamaio.Javacamp_Hrms_Backend.entities.concretes.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvManager implements CvService {

    private CvDao cvDao;
    private CandidateDao candidateDao;


    @Autowired
    public CvManager(CvDao cvDao,CandidateDao candidateDao) {
        this.cvDao = cvDao;
        this.candidateDao=candidateDao;

    }

    @Override
    public Result add(int candidateId) {
        Cv cv=new Cv();
        cv.setCandidate(this.candidateDao.getById(candidateId));

        this.cvDao.save(cv);
        return new SuccessResult("Kaydedildi");
    }



    @Override
    public DataResult<List<Cv>> getAll() {
        return new SuccessDataResult<>(this.cvDao.findAll(),"Data listelendi");
    }

    @Override
    public DataResult<Cv> getByCvId(int cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorDataResult("Böyle bir cv yok");
        }
        return new SuccessDataResult<Cv>(this.cvDao.getById(cvId),"Data listelendi");
    }

    @Override
    public DataResult<Cv> getByCandidateId(int candidateId) {
        if(this.cvDao.findByCandidateId(candidateId) == null){
            return new ErrorDataResult<Cv>("Böyle bir candidate yok");
        }
        return new SuccessDataResult<Cv>(this.cvDao.findByCandidateId(candidateId),"Data listelendi");
    }

    @Override
    public Result updateGithub(String github, int cvId) {

        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }else if(!github.startsWith("https://github.com")){
            if(!github.startsWith("github.com")){
                return new ErrorResult("Geçerli bir github linki değil");
            }
        }

        Cv cv=this.cvDao.getById(cvId);
        cv.setGithub(github);
        this.cvDao.save(cv);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result deleteGithub(int cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setGithub(null);
        this.cvDao.save(cv);

        return new SuccessResult("Github adresi kaldırıldı");
    }

    @Override
    public Result updateLinkedin(String linkedin, int cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }else if(!linkedin.startsWith("https://www.linkedin.com") &&
                !linkedin.startsWith("www.linkedin.com") &&
                !linkedin.startsWith("https://linkedin.com") &&
                !linkedin.startsWith("linkedin.com")){
            return new ErrorResult("Geçerli bir linked in adresi değil");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setLinkedin(linkedin);
        this.cvDao.save(cv);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result deleteLinkedin(int cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setLinkedin(null);
        this.cvDao.save(cv);
        return new SuccessResult("Linkedin adresi silindi");
    }

    @Override
    public Result updateBiography(String biography, int cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }else if(biography.length()<=2){
            return new ErrorResult("Biyografi 2 krakterden uzun olmalıdır");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setBiography(biography);
        this.cvDao.save(cv);
        return new SuccessResult("Biyografi kaydedildi");
    }

    @Override
    public Result deleteBiography(int cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setBiography(null);
        this.cvDao.save(cv);
        return new SuccessResult("Biyografi silindi");
    }

}
