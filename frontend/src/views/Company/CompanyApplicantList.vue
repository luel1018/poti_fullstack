<script setup>
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'

// 명함 컴포넌트 불러오기
import NamecardsFront from '@/components/namecards/NamecardsFront.vue'
import NamecardsBack from '@/components/namecards/NamecardsBack.vue'

const router = useRouter()
const route = useRoute()

const jobTitle = ref('[플랫폼본부] 프렌차이즈시너지그룹 AI 에이전트 엔지니어 (AI 어시스턴트)')

// 모달 및 명함 상태
const isModalOpen = ref(false)
const isFlipped = ref(false)
const selectedApplicant = ref(null)

// 지원자 상태 필터
const currentFilter = ref('ALL')

// 지원자 목록 데이터
const applicants = ref([
  {
    id: 101,
    name: '김철수',
    email: 'chulsu.kim@example.com',
    role: 'Backend Developer',
    experience: '경력 5년',
    education: '한국대학교 컴퓨터공학 전공',
    appliedAt: '2026-01-15',
    status: 'NEW',
    isFavorite: false,
    tags: ['Java', 'Spring', 'MySQL'],
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Chulsu',
  },
  {
    id: 102,
    name: '이영희',
    email: 'younghee.lee@example.com',
    role: 'AI Researcher',
    experience: '신입',
    education: '미래과학대학교 AI학부 졸업',
    appliedAt: '2026-01-16',
    status: 'INTERVIEW',
    isFavorite: true,
    tags: ['Python', 'PyTorch', 'NLP'],
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Younghee',
  },
  {
    id: 103,
    name: '박민준',
    email: 'minjun.park@example.com',
    role: 'Frontend Developer',
    experience: '경력 3년',
    education: '글로벌대학교 소프트웨어공학',
    appliedAt: '2026-01-14',
    status: 'PASSED',
    isFavorite: false,
    tags: ['Vue.js', 'React', 'TypeScript'],
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Minjun',
  },
])

// 즐겨찾기 토글
const toggleFavorite = (id) => {
  const applicant = applicants.value.find((a) => a.id === id)
  if (applicant) applicant.isFavorite = !applicant.isFavorite
}

// 필터링 목록
const filteredApplicants = computed(() => {
  if (currentFilter.value === 'ALL') return applicants.value
  if (currentFilter.value === 'FAVORITE') return applicants.value.filter((a) => a.isFavorite)
  return applicants.value.filter((a) => a.status === currentFilter.value)
})

// 명함 열기
const openCard = (applicant) => {
  selectedApplicant.value = applicant
  isFlipped.value = false // 초기화
  isModalOpen.value = true
}

const closeCard = () => {
  isModalOpen.value = false
  selectedApplicant.value = null
}

const goBack = () => router.push('/company/joblist')
</script>

<template>
  <div class="min-h-screen bg-pattern text-zinc-900 dark:text-zinc-100 font-sans transition-colors">
    <main class="max-w-7xl mx-auto px-6 py-10">
      <div class="mb-10">
        <button @click="goBack"
          class="flex items-center gap-2 text-zinc-400 hover:text-indigo-600 transition-colors mb-4 group font-medium">
          <svg xmlns="http://www.w3.org/2000/svg" class="w-5 h-5 group-hover:-translate-x-1 transition-transform"
            fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
          </svg>
          공고 목록으로 돌아가기
        </button>
        <h1 class="text-3xl font-bold tracking-tight mb-2">{{ jobTitle }}</h1>
      </div>

      <div
        class="bg-white dark:bg-zinc-900 rounded-[2.5rem] border border-zinc-100 dark:border-zinc-800 shadow-sm overflow-hidden">

        <div class="px-8 pt-8 flex items-center gap-6 border-b border-zinc-50 dark:border-zinc-800 overflow-x-auto">
          <button v-for="f in [
            { id: 'ALL', n: '전체' },
            { id: 'FAVORITE', n: '즐겨찾기' },
            { id: 'NEW', n: '신규' },
            { id: 'INTERVIEW', n: '면접' },
            { id: 'PASSED', n: '합격' },
            { id: 'FAILED', n: '불합격' },
          ]" :key="f.id" @click="currentFilter = f.id" :class="currentFilter === f.id
            ? 'text-indigo-600 border-b-2 border-indigo-600 pb-4 font-bold whitespace-nowrap'
            : 'text-zinc-400 pb-4 font-medium hover:text-zinc-600 transition-colors whitespace-nowrap'
            ">
            {{ f.n }}
          </button>
        </div>

        <div
          class="hidden lg:grid lg:grid-cols-12 px-8 py-4 bg-zinc-50/50 dark:bg-zinc-800/30 text-[11px] font-bold text-zinc-400 uppercase tracking-widest border-b border-zinc-50 dark:border-zinc-800">
          <div class="col-span-5">지원자 정보</div>
          <div class="col-span-3 text-center">학력 및 주요 경력</div>
          <div class="col-span-2 text-center">지원일</div>
          <div class="col-span-2 text-right">프로필</div>
        </div>

        <div class="divide-y divide-zinc-50 dark:divide-zinc-800">
          <div v-for="applicant in filteredApplicants" :key="applicant.id"
            class="p-8 hover:bg-zinc-50/50 dark:hover:bg-zinc-950 transition-colors group">
            <div class="grid grid-cols-1 lg:grid-cols-12 items-center gap-6">
              <div class="lg:col-span-5 flex items-center gap-4">
                <div class="relative">
                  <div
                    class="w-14 h-14 rounded-2xl bg-indigo-50 dark:bg-indigo-900/20 flex items-center justify-center overflow-hidden">
                    <img :src="applicant.avatar" class="w-full h-full object-cover" />
                  </div>
                  <button @click.stop="toggleFavorite(applicant.id)"
                    class="absolute -top-2 -left-2 w-6 h-6 rounded-full bg-white dark:bg-zinc-800 shadow-md flex items-center justify-center transition-transform hover:scale-110">
                    <svg xmlns="http://www.w3.org/2000/svg" class="w-3.5 h-3.5"
                      :class="applicant.isFavorite ? 'fill-amber-400 text-amber-400' : 'text-zinc-300'"
                      viewBox="0 0 24 24" stroke="currentColor">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                        d="M11.48 3.499a.562.562 0 011.04 0l2.125 5.111a.563.563 0 00.475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 00-.182.557l1.285 5.385a.562.562 0 01-.84.61l-4.725-2.885a.563.563 0 00-.586 0L6.982 20.54a.562.562 0 01-.84-.61l1.285-5.386a.562.562 0 00-.182-.557l-4.204-3.602a.563.563 0 01.321-.988l5.518-.442a.563.563 0 00.475-.345L11.48 3.5z" />
                    </svg>
                  </button>
                </div>
                <div>
                  <h3 class="text-lg font-bold group-hover:text-indigo-600 transition-colors">
                    {{ applicant.name }}
                  </h3>
                  <p class="text-sm text-zinc-400">{{ applicant.email }}</p>
                </div>
              </div>

              <div class="lg:col-span-3 text-center">
                <p class="text-sm font-bold text-zinc-700 dark:text-zinc-200">
                  {{ applicant.experience }}
                </p>
                <p class="text-xs text-zinc-400 mt-1">{{ applicant.education }}</p>
              </div>

              <div class="lg:col-span-2 text-center text-sm font-medium text-zinc-500">
                {{ applicant.appliedAt }}
              </div>

              <div class="lg:col-span-2 flex justify-end items-center">
                <button @click="openCard(applicant)"
                  class="px-5 py-2.5 rounded-xl font-bold bg-indigo-50 dark:bg-indigo-900/20 text-indigo-600 hover:bg-indigo-600 hover:text-white transition-all text-sm">
                  명함보기
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>

    <div v-if="isModalOpen && selectedApplicant" class="fixed inset-0 z-50 flex items-center justify-center p-6">
      <div class="absolute inset-0 bg-zinc-950/60 backdrop-blur-sm" @click="closeCard"></div>

      <div class="perspective-container w-[450px] aspect-[1.58/1]">
        <div class="card-object shadow-2xl cursor-pointer" :class="{ 'is-flipped': isFlipped }"
          @click="isFlipped = !isFlipped">
          <div class="w-full h-full card-face card-front bg-white rounded-2xl overflow-hidden">
            <NamecardsFront class="w-full h-full" :userId="selectedApplicant.id" />
          </div>

          <div class="w-full h-full card-face card-back bg-white rounded-2xl overflow-hidden">
            <NamecardsBack class="w-full h-full" :userId="selectedApplicant.id" />
          </div>
        </div>

        <button @click.stop="closeCard"
          class="absolute -top-12 right-0 text-white/80 hover:text-white transition-colors flex items-center gap-1 font-bold">
          <span>Close</span>
          <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </button>

        <p class="absolute -bottom-10 w-full text-center text-white/50 text-sm">
          카드를 클릭하면 뒤집힙니다
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 3D 플립 애니메이션 스타일 */
.perspective-container {
  perspective: 1200px;
  position: relative;
  z-index: 60;
}

.card-object {
  position: relative;
  width: 100%;
  height: 100%;
  /* 변수 적용 */
  transition: transform var(--flip-duration, 0.7s) var(--flip-easing, ease-in-out);
  transform-style: preserve-3d;
}

.card-object.is-flipped {
  transform: rotateY(180deg);
}

.card-face {
  position: absolute;
  inset: 0;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  border-radius: 1rem;
  overflow: hidden;
  box-shadow: 0 10px 30px -5px rgba(0, 0, 0, 0.1);
}

.card-front {
  transform: rotateY(0deg);
  z-index: 2;
}

.card-back {
  transform: rotateY(180deg);
  z-index: 1;
}

/* 기존 유틸리티 스타일 유지 */
.rounded-\[2\.5rem\] {
  border-radius: 2.5rem;
}

.overflow-x-auto {
  -ms-overflow-style: none;
  scrollbar-width: none;
}

.overflow-x-auto::-webkit-scrollbar {
  display: none;
}

.bg-pattern {
  background-color: #f8fafc;
}

.dark .bg-pattern {
  background-color: #18181b;
}
</style>